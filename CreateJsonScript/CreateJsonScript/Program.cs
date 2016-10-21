namespace CreateJsonScript
{
    using System.IO;

    class Program
    {
        static void Main(string[] args)
        {            
            string startCmd = "curl - X PATCH - d '{";
            string endCmd = "}' 'https://smartpark-aa8eb.firebaseio.com/parking.json'";
            string projectPath = @"C:\VisualStudio2015\CreateJsonScript";

            //using (StreamWriter sw = new StreamWriter(@"C:\Users\grasopper\Desktop\initScript.txt"))
            //{
            //    sw.WriteLine(startCmd);
            //    for (int i = 0; i < spaceArray.Length - 1; i++)
            //    {
            //        sw.WriteLine("\"" + spaceArray[i] + "\" : { \"is_available\" : true },");
            //    }
            //    sw.WriteLine("\"" + spaceArray[spaceArray.Length - 1] + "\" : { \"is_available\" : true }");
            //    sw.WriteLine(endCmd);

            //}



            // North garage has 4 floors
            for (int floor = 1; floor <= 4; floor++)
            {
                string[] northSpaces = File.ReadAllLines(projectPath + @"\n" + floor + ".txt");
                for (int i = 0; i < 10; i++)
                {
                    string[] splitStr = northSpaces[i].Split(new char[] { '\t' });
                    int spaceNumber = int.Parse(splitStr[0]);
                    string spaceType = "";

                    if (splitStr.Length > 1)
                    {
                        spaceType = splitStr[1];
                    }                    
                }                
            }

            // South garage has 5 floors
            for (int floor = 1; floor <= 5; floor++)
            {
                string[] southSpaces = File.ReadAllLines(projectPath + @"\s" + floor + ".txt");
                for (int i = 0; i < 10; i++)
                {
                    string[] splitStr = southSpaces[i].Split(new char[] { '\t' });
                    int spaceNumber = int.Parse(splitStr[0]);
                    string spaceType = "";

                    if (splitStr.Length > 1)
                    {
                        spaceType = splitStr[1];
                    }                    
                }
            }            
        }
    }
}
