
public class Upload {
	FirebaseOptions options = new FirebaseOptions.Builder()
	  .setServiceAccount(new FileInputStream("path/to/serviceAccountCredentials.json"))
	  .setDatabaseUrl("https://databaseName.firebaseio.com/")
	  .build();
	FirebaseApp.initializeApp(options);
}
