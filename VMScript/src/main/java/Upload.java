import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

public class Upload {
	public void firebase() {
		FirebaseOptions options;
		try {
			options = new FirebaseOptions.Builder()
			  .setServiceAccount(new FileInputStream("path/to/serviceAccountCredentials.json"))
			  .setDatabaseUrl("https://databaseName.firebaseio.com/")
			  .build();
			FirebaseApp.initializeApp(options);
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
