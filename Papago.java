package trans;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


public class Papago {
	
	private String clientId = "#";			
    private String clientSecret = "#";					
	private String result;
	
	public void Trans_data(String sentence) {
		try {
            String text = URLEncoder.encode(sentence, "UTF-8");
            String apiURL = "https://openapi.naver.com/v1/language/translate";
            
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("X-Naver-Client-Id", this.clientId);
            con.setRequestProperty("X-Naver-Client-Secret", this.clientSecret);
            
            
            // post request
            String postParams = "source=en&target=ko&text=" + text;
            
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            
            wr.writeBytes(postParams);
            wr.flush();
            wr.close();
            
            int responseCode = con.getResponseCode();      
            
            BufferedReader br;
            
            if(responseCode==200) { 
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else { 
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            
            String inputLine;
            StringBuffer response = new StringBuffer();
            
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            
            br.close();
            
           String num = (response.toString());
           String[] arr = num.split(":");
           arr = arr[6].split(",");
           
           this.result = arr[0].replaceAll("[^\uAC00-\uD7A3xfe0-9a-zA-Z\\s]","");

            
        } catch (Exception e) {
            System.out.println(e);
        }
	}
	
	public String Trans_result_get() {
		return this.result;
	}
	
    
}
