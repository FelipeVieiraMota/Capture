package services;

import com.google.gson.Gson;
import domains.vo.CaptureEntityParameters;
import domains.vo.CaptureResponse;
import domains.vo.CaptureResponseEntity;
import domains.vo.ResultEntity;
import util.http.HandleHttp;

public class CaptureServices {

    public CaptureResponse filterParameters(String json){
      Gson gson = new Gson();
      CaptureResponseEntity entity = gson.fromJson(json, CaptureResponseEntity.class);
      ResultEntity rs = entity.getResult();
      String id = entity.getId();
      String code = rs.getCode();
      String description = rs.getDescription();
      CaptureResponse response = new CaptureResponse(id,code,description);
      return response;
    }

    public static CaptureResponse createCaptureRequest(String id){
      CaptureEntityParameters captureEntityParameters = new CaptureEntityParameters(
        System.getenv("entity_id"),
        "10.00",
        "EUR",
        "CP"
      );
      HandleHttp handle = new HandleHttp();
      return handle.doPostRequest(captureEntityParameters, id);
    }
}
