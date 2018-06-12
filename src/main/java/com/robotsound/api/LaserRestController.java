package com.robotsound.api;


import com.robotsound.model.Laser;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@CrossOrigin
@RequestMapping(path = "/laser")
public class LaserRestController {

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    void setLaserPosition(HttpServletResponse response, @RequestBody Laser laser) throws IOException {
        System.out.println("LASER : " + laser.toString());
        //Try exec load or unload laser (1 or 0)
        try {
            Runtime rt = Runtime.getRuntime();
            Process proc;
            if (laser != null) {
                proc = rt.exec("java -jar /home/pi/robot/laser/LaserRobot.jar 1");
            } else {
                proc = rt.exec("java -jar /home/pi/robot/laser/LaserRobot.jar 0");
            }
            // setter exitValue for front loader
            int exitVal = proc.waitFor();
            System.out.println("Process exitValue: " + exitVal);

            response.setStatus(200);
        } catch (Throwable t) {
            t.printStackTrace();
            response.sendError(404, "Error execute runtime");
        }


    }

}
