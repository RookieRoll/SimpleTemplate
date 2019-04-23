package com.kobold.codegenerates;

import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.NullCacheStorage;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import java.io.IOException;
import java.io.Writer;

public class FreeMarkerTemplateUtils {
	private FreeMarkerTemplateUtils() {
	}

	private static final Configuration CONFIGURATION = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
	private static final String TemplateUrl = "/templates";

	static {
		//这里比较重要，用来指定加载模板所在的路径
		CONFIGURATION.setTemplateLoader(new ClassTemplateLoader(FreeMarkerTemplateUtils.class, TemplateUrl));
		CONFIGURATION.setDefaultEncoding("UTF-8");
		CONFIGURATION.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		CONFIGURATION.setCacheStorage(NullCacheStorage.INSTANCE);
	}

	public static Template getTemplate(String templateName) throws IOException {
		try {
			return CONFIGURATION.getTemplate(templateName);
		} catch (IOException e) {
			throw e;
		}
	}

	public static boolean processTemplate(String templateName, Object dataModel, Writer out) throws IOException, TemplateException {
		Template template = getTemplate(templateName);
		template.process(dataModel, out);
		return true;
	}

	public static void clearCache() {
		CONFIGURATION.clearTemplateCache();
	}
}
