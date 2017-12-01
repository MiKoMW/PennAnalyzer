import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Created by Mac on 2017/11/20.
 */
public class FileIO {

    public static ArrayList<String> readLine(String filePath) throws IOException
    {
        Charset charset = Charset.forName("utf-8");
        FileInputStream fileStream = new FileInputStream(new File(filePath));
        BufferedReader buffer = new BufferedReader(new InputStreamReader(fileStream,"utf-8"));
        ArrayList<String> ans = new ArrayList<String>();
        String line;
        while ((line = buffer.readLine()) != null){
            ans.add(line);
        }
        return ans;
    }

    public static void writeLine(ArrayList<String> sts, String OutPutFile) throws IOException{

        FileWriter fileOutput = new FileWriter(OutPutFile);
        BufferedWriter bufferOut = new BufferedWriter(fileOutput);
        File fileOut = new File(OutPutFile);
        if (!fileOut.exists()) {
            try {
                fileOut.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            for(String st: sts){
                bufferOut.write(st);
                bufferOut.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            bufferOut.close();
        }
    }
}
