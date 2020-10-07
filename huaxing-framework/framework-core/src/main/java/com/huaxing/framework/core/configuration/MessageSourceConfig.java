package com.huaxing.framework.core.configuration;

import com.huaxing.framework.core.exception.HbException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.util.StringUtils;

import java.io.File;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarFile;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;

@Configuration
public class MessageSourceConfig {

    private final static Logger logger = LoggerFactory.getLogger(MessageSourceConfig.class);

    @Value(value = "${spring.messages.baseFolder:i18n}")
    private String baseFolder;

    @Value(value = "${spring.messages.basename:message}")
    private String parentBaseName;

    @Bean(name = "messageSource")
    public ResourceBundleMessageSource getReloadableResourceBundleMessageSource() {
        ResourceBundleMessageSource resourceBundleMessageSource
                = new ResourceBundleMessageSource();

        this.registerAllI18nFile(resourceBundleMessageSource);
        resourceBundleMessageSource.setDefaultEncoding(StandardCharsets.UTF_8.name());
        return resourceBundleMessageSource;
    }


    /**
     * 注册所有配置文件
     * @param resourceBundleMessageSource
     */
    private void registerAllI18nFile(ResourceBundleMessageSource resourceBundleMessageSource) {

        logger.info("init MessageResourceExtension...");
        if (!StringUtils.isEmpty(baseFolder)) {
            try {
                resourceBundleMessageSource.setBasenames(this.getAllBaseNames(baseFolder));
            } catch (Exception e) {
                logger.error("init MessageResourceExtension fail {}",e);
            }
        }
        //设置父MessageSource
        ResourceBundleMessageSource parent = new ResourceBundleMessageSource();
        parent.setBasename(parentBaseName);
        resourceBundleMessageSource.setParentMessageSource(parent);
    }

    /**
     * 获取文件夹下所有的国际化文件名
     * @param baseFolder
     * @return
     */
    private  String[] getAllBaseNames(String baseFolder) throws Exception {
        List<String> baseNames = new ArrayList<>();

        URL url = Thread.currentThread().getContextClassLoader().getResource(baseFolder);
        if(null == url){
            throw new HbException("无法获取资源文件路径");
        }

        // 文件夹形式,用File获取资源路径
        if (url.getProtocol().equalsIgnoreCase("file")) {
            File file = new File(url.getFile());
            if (file.exists() && file.isDirectory()) {
                baseNames = Files.walk(file.toPath())
                        .filter(path -> path.toFile().isFile())
                        .map(Path::toString)
                        .map(path -> path.substring(path.indexOf(baseFolder)))
                        .map(this::getI18FileName)
                        .distinct()
                        .collect(Collectors.toList());
            } else {
                logger.error("指定的baseFile不存在或者不是文件夹");
            }

            // jar包形式，用JarEntry获取资源路径
        } else if (url.getProtocol().equalsIgnoreCase("jar")) {
            String jarPath = url.getFile().substring(url.getFile().indexOf(":") + 1, url.getFile().indexOf("!"));
            logger.info("{} parse jar file :{}",url.getFile(),jarPath);
            JarFile jarFile = new JarFile(new File(jarPath));
            List<String> baseJars = jarFile.stream()
                    .map(ZipEntry::toString)
                    .filter(jar -> jar.endsWith(baseFolder + "/")).collect(Collectors.toList());
            if (baseJars.isEmpty()) {
                logger.info("不存在{}资源文件夹", baseFolder);
                return new String[0];
            }

            baseNames = jarFile.stream().map(ZipEntry::toString)
                    .filter(jar -> baseJars.stream().anyMatch(jar::startsWith))
                    .filter(jar -> jar.endsWith(".properties"))
                    .map(jar -> jar.substring(jar.indexOf(baseFolder)))
                    .map(this::getI18FileName)
                    .distinct()
                    .collect(Collectors.toList());

        }
        return baseNames.toArray(new String[0]);
    }


    /**
     * 把普通文件名转换成国际化文件名
     *
     * @param filename
     * @return
     */
    private String getI18FileName(String filename) {
        filename = filename.replace(".properties", "");
        for (int i = 0; i < 2; i++) {
            int index = filename.lastIndexOf("_");
            if (index != -1) {
                filename = filename.substring(0, index);
            }
        }
        return filename;
    }


}
