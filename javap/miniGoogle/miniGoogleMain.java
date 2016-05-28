class miniGoogleMain {
	public static void main(String[] args) {
		// webCrawler wbc = new webCrawler();
		String seedPage = "http://minigoogle.msitprogram.net";
		searchIndex si = new searchIndex();
		si.indexing(seedPage);
		//System.out.println(si);
	}
}