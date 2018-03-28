/**
** The MIT License
**
** Copyright 2018 Joe Rafferty, Ulster University.
**
** Permission is hereby granted, free of charge, to any person obtaining a copy
** of this software and associated documentation files (the "Software"), to deal
** in the Software without restriction, including without limitation the rights
** to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
** copies of the Software, and to permit persons to whom the Software is
** furnished to do so, subject to the following conditions:
**
** The above copyright notice and this permission notice shall be included in
** all copies or substantial portions of the Software.
**
** THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
** IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
** FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
** AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
** LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
** OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
** THE SOFTWARE.
 **/

package ulster.lpwan.datacaptureendpoint.endpoints;

/**
 *
 * @author Joe Rafferty - Ulster University
 */


import com.google.gson.Gson;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import ulster.lpwan.datacaptureendpoint.conf.AppConfig;
import ulster.lpwan.datacaptureendpoint.model.SensorData;
import ulster.lpwan.datacaptureendpoint.pojo.SensorRecord;
import ulster.lpwan.datacaptureendpoint.pojo.TCNMessage;


@Path("/TCNAdaptor")
public class ThingsConnectedNetworkAdaptor {
    

    AppConfig appConfig;
    Gson gson;
            
    public ThingsConnectedNetworkAdaptor()
    {
        appConfig = new AppConfig();
        gson  = new Gson();
    }
    
    //Handle GET operations
    @GET
    @Produces("text/plain")
    public String getOp(@QueryParam("token") String iKey){
        
        try{
        
            if(iKey.trim().equals(appConfig.apiKey)){
                return "Valid";
            }else{
                return "Invalid";
            }
            
        } catch (Exception e){
            return "You must supply an API key";
        }
        
    }

    //Handle POST operations
    @POST 
    public void postOp(
        @QueryParam("token") String iKey,
        String iStr) {
        
        try{
            if(iKey.trim().equals(appConfig.apiKey)){

                TCNMessage tcnMessage = new TCNMessage();


                //Parse TCN message
                tcnMessage = gson.fromJson(iStr, TCNMessage.class);
                
                //Create Sensor record
                SensorRecord sRec = new SensorRecord();
                
                sRec.dev_eui = tcnMessage.params.dev_eui.trim();
                sRec.payload = tcnMessage.params.payload;
                sRec.message = iStr;
                sRec.server_time = tcnMessage.params.rx_time;
                sRec.connectorTime = (int)System.currentTimeMillis();
                
                //Insert Sensor Data
                SensorData sData = new SensorData();
                sData.insert(sRec);

            }
        }catch(Exception e){
                
        }

    }
    
}
