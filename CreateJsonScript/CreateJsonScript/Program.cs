namespace CreateJsonScript
{
    using System.Collections.Generic;
    using System.IO;

    class Program
    {
        static List<Space>[] northSpaces;
        static List<Space>[] southSpaces;

        const string startCmd = "curl - X PATCH - d '{";
        const string endCmd = "}' 'https://smartpark-aa8eb.firebaseio.com/test.json'";
        const string projectPath = @"C:\Users\JohnBlaze\SmartPark\CSS-490-Smart-Park\CreateJsonScript";

        static void Main(string[] args)
        {   
            northSpaces = new List<Space>[4];
            southSpaces = new List<Space>[5];

            ParseFiles();

            TestOutput(); 
        }

        private static void ParseFiles()
        {
            // North garage has 4 floors
            for (int floor = 1; floor <= 4; floor++)
            {
                northSpaces[floor - 1] = new List<Space>();
                string[] floorSpaces = File.ReadAllLines(projectPath + @"\n" + floor + ".txt");

                for (int i = 0; i < floorSpaces.Length; i++)
                {
                    string[] splitStr = floorSpaces[i].Split(new char[] { '\t' });
                    int spaceNumber = int.Parse(splitStr[0]);
                    string spaceType = "";

                    if (splitStr.Length > 1)
                    {
                        if (splitStr[1] != "")
                        {
                            spaceType = splitStr[1];
                        }
                        else
                        {
                            spaceType = "REG";
                        }                        
                    }

                    northSpaces[floor - 1].Add(new Space(spaceNumber, spaceType, true, "North", floor));                    
                }
            }

            // South garage has 5 floors
            for (int floor = 1; floor <= 5; floor++)
            {
                southSpaces[floor - 1] = new List<Space>();
                string[] floorSpaces = File.ReadAllLines(projectPath + @"\s" + floor + ".txt");

                for (int i = 0; i < floorSpaces.Length; i++)
                {
                    string[] splitStr = floorSpaces[i].Split(new char[] { '\t' });
                    int spaceNumber = int.Parse(splitStr[0]);
                    string spaceType = "Reg";

                    if (splitStr.Length > 1)
                    {
                        if (splitStr[1] != "")
                        {
                            spaceType = splitStr[1];
                        }
                        else
                        {
                            spaceType = "REG";
                        }
                    }

                    southSpaces[floor - 1].Add(new Space(spaceNumber, spaceType, true, "South", floor));
                }
            }
        }

        private static void TestOutput()
        {
            for (int i = 0; i < northSpaces.Length; i++)
            {
                using (StreamWriter sw = new StreamWriter(projectPath + @"\northTest" + i + ".txt"))
                {
                    foreach (var space in northSpaces[i])
                    {
                        sw.WriteLine(space.SpaceNumber + " " + space.Floor + " " + space.IsAvailable + " " + space.Garage + " " + space.SpaceType);
                    }
                }
            }

            for (int i = 0; i < southSpaces.Length; i++)
            {
                using (StreamWriter sw = new StreamWriter(projectPath + @"\southTest" + i + ".txt"))
                {
                    foreach (var space in southSpaces[i])
                    {
                        sw.WriteLine(space.SpaceNumber + " " + space.Floor + " " + space.IsAvailable + " " + space.Garage + " " + space.SpaceType);
                    }
                }
            }
        }
    }
}
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

//// North garage has 4 floors
//for (int floor = 1; floor <= 4; floor++)
//{
//    string[] northSpaces = File.ReadAllLines(projectPath + @"\n" + floor + ".txt");
//    for (int i = 0; i < northSpaces.Length; i++)
//    {
//        string[] splitStr = northSpaces[i].Split(new char[] { '\t' });
//        int spaceNumber = int.Parse(splitStr[0]);
//        string spaceType = "";

//        if (splitStr.Length > 1)
//        {
//            spaceType = splitStr[1];
//        }
//    }
//}

//// South garage has 5 floors
//for (int floor = 1; floor <= 5; floor++)
//{
//    string[] southSpaces = File.ReadAllLines(projectPath + @"\s" + floor + ".txt");
//    for (int i = 0; i < southSpaces.Length; i++)
//    {
//        string[] splitStr = southSpaces[i].Split(new char[] { '\t' });
//        int spaceNumber = int.Parse(splitStr[0]);
//        string spaceType = "";

//        if (splitStr.Length > 1)
//        {
//            spaceType = splitStr[1];
//        }
//    }
//}  