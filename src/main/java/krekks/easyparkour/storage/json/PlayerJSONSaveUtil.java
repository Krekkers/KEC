package krekks.easyparkour.storage.json;

import krekks.easyparkour.playerdata.PlayerData;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class PlayerJSONSaveUtil {


    public void savePlayer(PlayerData pd){
        //Creating a JSONObject object
        JSONObject jsonObject = new JSONObject();
        //add values
        jsonObject.put("key", "value");
        //save data
        try {
            FileWriter file = new FileWriter("");
            file.write(jsonObject.toJSONString());
            file.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }


    public int getSavedPoints(PlayerData pd){
        //gets points

        //sets points
        pd.setPoints(1);

        return 0;
    }

}
