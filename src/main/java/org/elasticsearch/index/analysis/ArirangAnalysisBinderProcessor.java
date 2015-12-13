package org.elasticsearch.index.analysis;


public class ArirangAnalysisBinderProcessor	 extends AnalysisModule.AnalysisBinderProcessor {

	@Override
	public void processAnalyzers(AnalyzersBindings analyzersBindings) {
		analyzersBindings.processAnalyzer("arirang_analyzer", ArirangAnalyzerProvider.class);
		analyzersBindings.processAnalyzer("arirang_analyzer_test_one", ArirangAnalyzerProvider.class);
		analyzersBindings.processAnalyzer("arirang_analyzer_test_two", ArirangAnalyzerProvider.class);
	}

	@Override
	public void processTokenizers(TokenizersBindings tokenizersBindings) {
		tokenizersBindings.processTokenizer("arirang_tokenizer", ArirangTokenizerFactory.class);
		tokenizersBindings.processTokenizer("arirang_tokenizer_test_one", ArirangTokenizerFactory.class);
		tokenizersBindings.processTokenizer("arirang_tokenizer_test_two", ArirangTokenizerFactory.class);
	}

	@Override
	public void processTokenFilters(TokenFiltersBindings tokenFiltersBindings) {
		tokenFiltersBindings.processTokenFilter("arirang_filter", ArirangTokenFilterFactory.class);
		tokenFiltersBindings.processTokenFilter("arirang_filter_test_one", ArirangTokenFilterFactory.class);
		tokenFiltersBindings.processTokenFilter("arirang_filter_test_two", ArirangTokenFilterFactory.class);
	}
}
