package org.elasticsearch.index.analysis;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.ko.KoreanFilter;
import org.apache.lucene.analysis.ko.KoreanTokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.TypeAttribute;
import org.elasticsearch.Version;
import org.elasticsearch.cluster.metadata.IndexMetaData;

import org.elasticsearch.common.inject.Injector;
import org.elasticsearch.common.inject.ModulesBuilder;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.settings.SettingsModule;
import org.elasticsearch.env.Environment;
import org.elasticsearch.env.EnvironmentModule;
import org.elasticsearch.index.Index;
import org.elasticsearch.index.IndexNameModule;
import org.elasticsearch.index.settings.IndexSettingsModule;
import org.elasticsearch.indices.analysis.IndicesAnalysisService;
import org.elasticsearch.plugin.analysis.arirang.AnalysisArirangPlugin;

import org.junit.Test;

import org.elasticsearch.common.settings.ImmutableSettings;

public class ArirangAnalyzerTest {
	public String query = "크리스마스다이어리";

	@Test
	public void testArirangAnalyzerNamedAnalyzer() throws Exception {
		System.out.println("####### testArirangAnalyzerNamedAnalyzer #######");

		Index index = new Index("test");

		Settings settings = ImmutableSettings.settingsBuilder()
			.put("path.home", "/tmp")
			.put("index.analysis.analyzer.arirang.type", "arirang_analyzer")
			.put(IndexMetaData.SETTING_VERSION_CREATED, Version.CURRENT)
			.build();

		Injector parentInjector = new ModulesBuilder().add(new SettingsModule(settings),
														   new EnvironmentModule(new Environment(settings)))
			.createInjector();

		AnalysisModule analysisModule = new AnalysisModule(settings, parentInjector.getInstance(IndicesAnalysisService.class));
		new AnalysisArirangPlugin().onModule(analysisModule);

		Injector injector = new ModulesBuilder().add(
													 new IndexSettingsModule(index, settings),
													 new IndexNameModule(index),
													 analysisModule)
			.createChildInjector(parentInjector);

		AnalysisService analysisService = injector.getInstance(AnalysisService.class);
		NamedAnalyzer namedAnalyzer = analysisService.analyzer("arirang");

		TokenStream tokenStream = namedAnalyzer.tokenStream(null, query);

		CharTermAttribute termAtt = tokenStream.addAttribute(CharTermAttribute.class);
		TypeAttribute typeAttr = tokenStream.addAttribute(TypeAttribute.class);

		try {
			tokenStream.reset();

			while (tokenStream.incrementToken()) {
				System.out.println(termAtt.toString() + " [" + typeAttr.type() + "]");
			}

			tokenStream.end();
		} finally {
			tokenStream.close();
		}
	}
}
