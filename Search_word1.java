package trans;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Search_word1 {
	
	private Elements value;
	
	public void search_data(String word) throws IOException {
		Document doc = Jsoup.connect("http://alldic.daum.net/search.do?q=" + word).get();
		
		try {
			this.value = (doc.select(".list_search").first()).select("li > .txt_search");
		} catch(Exception e) {
		
		}
	}

	public String print_word() {
		return value.text();
	}
}
