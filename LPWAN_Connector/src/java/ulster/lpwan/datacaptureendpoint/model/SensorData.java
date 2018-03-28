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

package ulster.lpwan.datacaptureendpoint.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import ulster.lpwan.datacaptureendpoint.pojo.SensorRecord;

/**
 *
 * @author Joe Rafferty - Ulster University
 */

public class SensorData extends MySQLDB{
 
    public SensorData() {
        super();
    }
    
    //Get a list of Organisations
    public ArrayList<SensorRecord> getAll(){
    
        ArrayList<SensorRecord> ret = new ArrayList<SensorRecord>();
        
        SensorRecord sensRec;
        
        super.init();
        
        try{
            ResultSet rs = null;
            
            String pSql = "SELECT * FROM sensorData ORDER BY connectorTime DESC";

            PreparedStatement preparedStatement = con.prepareStatement(pSql);
             
            rs = preparedStatement.executeQuery();
            
            while (rs.next()) {
                
                sensRec = new SensorRecord();
                
                sensRec.recordID = rs.getInt(1);
                sensRec.dev_eui = rs.getString(2);
                sensRec.payload = rs.getString(3);
                sensRec.message = rs.getString(4);
                sensRec.connectorTime = rs.getInt(5);
                sensRec.server_time = rs.getDouble(6);
                
                //Pop to return array
                ret.add(sensRec);
            }
            
            con.close();
            con = null;
   
         }catch(Exception e){
            System.err.println(e);
         }   
        
        return ret;
    }
    
    //insert a sensor record object into the database
    public boolean insert(SensorRecord iRec){
        
        boolean ret = false;
        super.init();
        
         try{
             
            String sql = "INSERT INTO sensorData ("
                    + "dev_eui,"
                    + "payload,"
                    + "message,"
                    + "connectorTime,"
                    + "server_time)"
                    + "values(?,?,?,?,?)";
            
            PreparedStatement preparedStatement = con.prepareStatement(sql);

            preparedStatement.setString(1, iRec.dev_eui);
            preparedStatement.setString(2, iRec.payload);
            preparedStatement.setString(3, iRec.message);
            preparedStatement.setInt(4, iRec.connectorTime);
            preparedStatement.setDouble(5, iRec.server_time);
            
            preparedStatement.executeUpdate();
            
            con.close();
            con = null;
              
            ret = true;
            
        }catch(Exception e){
            System.err.println(e);    
        }
        
        return ret;
        
    }
 
}