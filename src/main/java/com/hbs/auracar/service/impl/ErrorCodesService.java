package com.hbs.auracar.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hbs.auracar.service.IErrorCodesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.*;

@Service
public class ErrorCodesService implements IErrorCodesService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorCodesService.class);

    List<Locale> locales = Arrays.asList(
            new Locale("es"),
            new Locale("en")
    );


    private Map<Locale, String> codes = new HashMap<>();
    private Map<Locale, Map<String, ErrorCode>> descriptions = new HashMap<>();

    /**
     * Initialize error codes JSON and Map from file
     * In case of failure, an empty object is loaded
     */
    @PostConstruct
    public void init() {
        for (Locale locale : locales) {
            ObjectMapper objectMapper = new ObjectMapper();
            ResourceLoader resourceLoader = new DefaultResourceLoader();
            Resource resource = resourceLoader.getResource("classpath:errorCodes." + locale.getLanguage() + ".json");
            try {
                codes.put(locale, new String(resource.getInputStream().readAllBytes()));
                descriptions.put(
                        locale,
                        objectMapper.readValue(resource.getInputStream().readAllBytes(), new TypeReference<Map<String, ErrorCode>>() {
                        })
                );
            } catch (IOException e) {
                codes.put(locale, "{}");
                descriptions.put(
                        locale,
                        new HashMap<>()
                );
                LOGGER.error(e.getLocalizedMessage());
            }
        }
    }

    private Locale getLocale(String languages) {
        if (languages == null) {
            languages = locales.get(0).getLanguage();
        }
        List<Locale.LanguageRange> ranges = Locale.LanguageRange.parse(languages);
        Locale locale = Locale.lookup(ranges, locales);
        if (locale == null) {
            locale = locales.get(0);
        }
        return locale;
    }

    @Override
    public String getCodes(String languages) {
        Locale locale = getLocale(languages);
        return this.codes.get(locale);
    }

    @Override
    public ErrorCode getCodeDescription(String languages, String code) {
        Locale locale = getLocale(languages);
        return descriptions.get(locale).get(code);
    }
}
