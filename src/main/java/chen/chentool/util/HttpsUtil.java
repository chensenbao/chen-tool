package chen.chentool.util;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import java.io.IOException;

/**
 * @description: https请求工具类
 * @author: sen
 */
public class HttpsUtil {

    /**
     * 发起https请求并获取结果
     *
     * @param requestUrl    请求地址
     */
    public static String httpGet(String requestUrl){
        CloseableHttpClient httpClient = null;
        HttpGet httpGet = new HttpGet(requestUrl);
        CloseableHttpResponse response = null;
        try {
            TrustManager[] tm = {new MyX509TrustManager()};
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            SSLConnectionSocketFactory scsf = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);
            httpClient = HttpClients.custom().setSSLSocketFactory(scsf).build();
            httpGet.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/125.0.0.0 Safari/537.36");
            httpGet.addHeader("Accept", "*/*");
            httpGet.addHeader("Accept-Encoding", "gzip, deflate, br, zstd");
            httpGet.addHeader("Connection", "keep-alive");
            httpGet.addHeader("Cache-Control", "max-age=0");
            httpGet.addHeader("Cookie", "_uab_collina=171737698842410228244773; JSESSIONID=0B6E00707A6B40F72AF221F95DD11532; BIGipServerotn=2229797130.64545.0000; BIGipServerpassport=904397066.50215.0000; guidesStatus=off; highContrastMode=defaltMode; cursorStatus=off; route=6f50b51faa11b987e576cdb301e545c4; _jc_save_toStation=%u8BCF%u5B89%2CZDS; _jc_save_fromDate=2024-06-03; _jc_save_toDate=2024-06-03; _jc_save_wfdc_flag=dc; _big_fontsize=0; _jc_save_zwdch_fromStation=%u798F%u5DDE%2CFZS; _jc_save_zwdch_cxlx=1; tfstk=fPISx-fhqbcSOQ6XtUe2cfYyy4KCN_ZZev9dI9nrp3K-9XB9gLppwH3Al6C2wTWUtDMpMOWPz0-8Aw11i0RJTe-QAsOXU4yk8kIfpOeoYLRPlv6GGH5CK8vp96CBTaz4bTXkxHFNAlrNEk2qTr1S20nYpdvnwf_xmiWkxHF4RbF9ZTf1lDdQRHeXDppI2HLK2EeXELtK2en-kjdDpHdd9UEvMdJKeLpp9ohqmA9QPQX7TEK4HXojUTOjvcMMrU35h2oxDh9WPMBJGT65cLTW6EWKKhjRg9IPqE4ozHXV56_d6SuywN91GF771011ZpCvH_yE-K_CpGxkyvmh131BWgLjpD692QxXHGeESISXa6IWkRo68nIwW3Q4o78eVd1dq_MIv1BVQgYcA5GvtaJMDps3B2OBJg8-bCG_x2MXSD9X_-wj-2-Sfjl0aPy3jUpDFEybhbQHyKvX_-wjwwYJnL9ah-GR-; _jc_save_fromStation=%u798F%u5DDE%2CFZS");
            httpGet.addHeader("Host", "kyfw.12306.cn");
            httpGet.addHeader("Sec-Ch-Ua", "\"Google Chrome\";v=\"125\", \"Chromium\";v=\"125\", \"Not.A/Brand\";v=\"24\"");
            httpGet.addHeader("Sec-Ch-Ua-Mobile", "?0");
            httpGet.addHeader("Sec-Ch-Ua-Platform", "\"Windows\"");
            httpGet.addHeader("Sec-Fetch-Dest", "document");
            httpGet.addHeader("Sec-Fetch-Mode", "navigate");
            httpGet.addHeader("Sec-Fetch-Site", "none");
            httpGet.addHeader("Sec-Fetch-User", "?1");
            httpGet.addHeader("Upgrade-Insecure-Requests", "1");
            response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            String string = EntityUtils.toString(entity, "UTF-8");
            if (string.contains("网络可能存在问题")) {
                System.out.println(requestUrl);
                System.out.println("出错了/请求频繁");
                return null;
            }
            return string;
        } catch (Exception e) {
//            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            try {
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
