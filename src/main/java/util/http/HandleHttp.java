package util.http;

import domains.vo.CaptureEntityParameters;
import domains.vo.CaptureResponse;
import services.CaptureServices;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;

public class HandleHttp {

  public CaptureResponse doPostRequest(CaptureEntityParameters captureEntityParameters, String id)  {
    CaptureResponse result = null;
    try{
      String data = captureEntityParameters.parseToString();
      URL url = new URL(generateUrl(id));

      HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
      conn.setRequestMethod("POST");
      conn.setRequestProperty("Authorization", System.getenv("authentication"));
      conn.setDoInput(true);
      conn.setDoOutput(true);

      DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
      wr.writeBytes(data);
      wr.flush();
      wr.close();
      int responseCode = conn.getResponseCode();
      InputStream is;

      if (responseCode >= 400)
        is = conn.getErrorStream();
      else
        is = conn.getInputStream();

      BufferedReader in = new BufferedReader(new InputStreamReader(is));

      StringBuilder response = new StringBuilder();
      String currentLine;

      while ((currentLine = in.readLine()) != null)
        response.append(currentLine);

      in.close();

      CaptureServices service = new CaptureServices();
      result = service.filterParameters(response.toString());
    }catch (IOException io){
      io.printStackTrace();
    }
    return result;
  }

  private String generateUrl(String id){
    return System.getenv("url_end_point")+"/"+id;
  }
}
