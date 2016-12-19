package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 * @author thsc
 */
public class Parameter {
    private String servername;
    private String portnumber;
    private String username;
    private String pwd;
    private String dbname;
    private String schema;
    
    public Parameter(String filename) throws FileNotFoundException, IOException {
        FileInputStream fInput = new FileInputStream(filename);
        File file = new File(filename);
        FileReader fr = new FileReader(file);
        
        BufferedReader br = new BufferedReader(fr);
        
        String inLine = br.readLine();
        while(inLine != null) {
            StringTokenizer st = new StringTokenizer(inLine, ":");
            if(st.hasMoreTokens()) {
                String key, value;
                key = st.nextToken();
                if(st.hasMoreTokens()) {
                    value = st.nextToken();
                
                    // fill parameters
                    switch(key) {
                        case "servername": this.servername = value; break;
                        case "portnumber": this.portnumber = value; break;
                        case "username": this.username = value; break;
                        case "pwd": this.pwd = value; break;
                        case "dbname": this.dbname = value; break;
                        case "schema": this.schema = value; break;
                    }
                }
            }
            // next line
            inLine = br.readLine();
        }
    }
    
    public String getServerName() { return this.servername ;}
    public String getPortNumber() { return this.portnumber ;}
    public String getUserName() { return this.username ;}
    public String getPWD() { return this.pwd ;}
    public String getdbName() { return this.dbname ;}
    public String getSchema() { return this.schema ;}
    
    public String getPath() { return this.getdbName() ;}
}
