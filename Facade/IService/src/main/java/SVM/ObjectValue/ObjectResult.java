package SVM.ObjectValue;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ObjectResult {
    private List<BusinessError> businessErrors;
    private Object result;
    private List<String> relatedLinks;
    private Trace trace;
    private int traceNO;


    public ObjectResult() {
        businessErrors = new ArrayList<>();
        relatedLinks = new ArrayList<>();
        trace = new Trace();
    }

    public static ObjectResult get(Object result) {
        return get(null,result,null);
    }

    public static ObjectResult get(List<BusinessError> businessErrors, Object result, List<String> relatedLinks) {
        ObjectResult obj = new ObjectResult();
        obj.setBusinessErrors(businessErrors);
        obj.setResult(result);
        obj.setRelatedLinks(relatedLinks);
        obj.setTraceNO(0);
        return obj;
    }

    public static ObjectResult get(List<BusinessError> businessErrors, Object result, List<String> relatedLinks, int testNo) {
        ObjectResult obj = get(businessErrors, result, relatedLinks);
        obj.setTraceNO(testNo);
        return obj;
    }

    public static ObjectResult setError(String json) throws IOException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<BusinessError> participantJsonList = mapper.readValue(json, new TypeReference<List<BusinessError>>() {
            });

            return ObjectResult.get(participantJsonList, null, null);
        } catch (Exception ex) {
            List<BusinessError> bss = new ArrayList<>();
            BusinessError bs = new BusinessError();
            bs.setCode("103");
            bs.setMessage(json);
            bss.add(bs);
            return ObjectResult.get(bss, null, null);
        }
    }

    public List<BusinessError> setError(String code, String message) {
        BusinessError be = new BusinessError();
        be.setCode(code);
        be.setMessage(message);
        businessErrors.add(be);
        return businessErrors;
    }

    public List<BusinessError> getBusinessErrors() {
        return businessErrors;
    }

    public void setBusinessErrors(List<BusinessError> businessErrors) {
        this.businessErrors = businessErrors;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public List<String> getRelatedLinks() {
        return relatedLinks;
    }

    public void setRelatedLinks(List<String> relatedLinks) {
        this.relatedLinks = relatedLinks;
    }


    public Trace getTrace() {
        return trace;
    }

    public void setTrace(Trace trace) {
        this.trace = trace;
    }

    public int getTraceNO() {
        return traceNO;
    }

    public void setTraceNO(int traceNO) {
        this.traceNO = traceNO;
    }


}
