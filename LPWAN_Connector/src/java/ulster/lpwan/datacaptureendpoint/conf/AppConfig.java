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

package ulster.lpwan.datacaptureendpoint.conf;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 *
 * @author Joe Rafferty - Ulster University
 */

@ApplicationPath("REST")
public class AppConfig extends Application {

    /*
    * An API key to Secure data access - CHANGE THIS FROM THE DEFAULT
    * I suggest obtaining one from https://www.grc.com/passwords.htm 
    * Use the 63 random alpha-numeric characters (a-z, A-Z, 0-9) entry.
    */
    public static String apiKey = "Qj73pC8odaOemxlEq4iyWvkS4jxg1DXnfNH7sCDMDO8guhomWurnxrWxCpJhfHm";
    
    
    /*
    MySQL Database Paramaters
    */
    
    //The URI/IP address of the mySql Server encoded as a JDBC conn
    public static String mySqlAddress = "jdbc:mysql://192.168.0.61:3306/";
    
    //The schema for the mySqlDatabase - default is TC_LORA
    public static String mySqlSchema = "TC_LORA";
    
    //The Username for the SQL database
    public static String mySqlUsername = "TC_LORA";
    
    //The Password for the SQL user - CHANGE THIS
    public static String mySqlPw = "mPsLPkL7ui1wwNCu7cJQH0u";
    
    
}
