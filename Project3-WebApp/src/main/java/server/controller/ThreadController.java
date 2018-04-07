package server.controller;

import server.service.FaceServer;
import server.view.DetectionPanel;
import server.view.EmoticonComposer;
import utility.FaceData;

public class ThreadController implements Runnable {

	static Thread th;
	DetectionPanel dpanel ; 
	Double emointerval = 1.0;
	Double timeElapsed = 0.0;
	public ThreadController(Double emointerval, DetectionPanel dpanel ){	
		th= new Thread(this);
		this.dpanel=dpanel;
		this.emointerval= emointerval;
		timeElapsed = Double.parseDouble(dpanel.timeElapsedTextbox.getText());
	}
	
	public void run() {
		while(true){
			timeElapsed += emointerval;
			//System.out.println(timeElapsed);
			try {
				th.sleep((long) (1000 * emointerval));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dpanel.timeElapsedTextbox.setText(timeElapsed+"");
			FaceData faceData = dpanel.getDetectionController().createFaceDataInstance();
			faceData.setTimeElapsed(timeElapsed);
			FaceServer.put(faceData);
		}
	}

	public static void stop() {
		th.stop();
	}
	
	public static void start(){
	
		th.start();
	}
}
