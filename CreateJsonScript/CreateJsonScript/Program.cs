namespace CreateJsonScript
{
    using System.Collections.Generic;
    using System.IO;

    class Program
    {
        static List<Space>[] northSpaces;
        static List<Space>[] southSpaces;
        
        const string projectPath = @"C:\Users\JohnBlaze\SmartPark\CSS-490-Smart-Park\CreateJsonScript";

        static void Main(string[] args)
        {   
            northSpaces = new List<Space>[4];
            southSpaces = new List<Space>[5];

            ParseFiles();
            WriteScript();
        }

        #region ParseFiles
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
        #endregion

        #region TestOutput
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
        #endregion

        static void WriteScript()
        {
            const string startCmd = "curl -X PATCH -d '{";
            const string openNorth = "\"NorthGarage\" : {";
            const string openSouth = "\"SouthGarage\" : {";

            const string endCmd = "}' 'https://smartpark-aa8eb.firebaseio.com/test.json'";

            using (StreamWriter sw = new StreamWriter(projectPath + @"\initScript.txt"))
            {
                sw.WriteLine(startCmd);

                sw.WriteLine(openNorth);

                for (int i = 0; i < northSpaces.Length; i++)
                {
                    sw.WriteLine("\"Floor" + (i + 1) + "\" : {");

                    for (int j = 0; j < northSpaces[i].Count; j++)
                    {                        
                        sw.WriteLine("\"" + northSpaces[i][j].SpaceNumber + "\" : {");
                        sw.WriteLine("\"garage\" : \"" + northSpaces[i][j].Garage + "\",");
                        sw.WriteLine("\"floor\" : " + northSpaces[i][j].Floor + ",");
                        sw.WriteLine("\"isAvailable\" : " + northSpaces[i][j].IsAvailable.ToString().ToLower() + ",");
                        sw.WriteLine("\"spaceNumber\" : " + northSpaces[i][j].SpaceNumber + ",");
                        sw.WriteLine("\"spaceType\" : \"" + northSpaces[i][j].SpaceType + "\"");

                        if (j < northSpaces[i].Count - 1)
                        {
                            sw.WriteLine("},");
                        }
                        else
                        {
                            sw.WriteLine("}");
                        }
                    }

                    if (i < northSpaces.Length - 1)
                    {
                        sw.WriteLine("},");
                    }
                    else
                    {
                        sw.WriteLine("}");
                    }
                }

                sw.WriteLine("},");
                sw.WriteLine(openSouth);

                for (int i = 0; i < southSpaces.Length; i++)
                {
                    sw.WriteLine("\"Floor" + (i + 1) + "\" : {");

                    for (int j = 0; j < southSpaces[i].Count; j++)
                    {
                        sw.WriteLine("\"" + southSpaces[i][j].SpaceNumber + "\" : {");
                        sw.WriteLine("\"garage\" : \"" + southSpaces[i][j].Garage + "\",");
                        sw.WriteLine("\"floor\" : " + southSpaces[i][j].Floor + ",");
                        sw.WriteLine("\"isAvailable\" : " + southSpaces[i][j].IsAvailable.ToString().ToLower() + ",");
                        sw.WriteLine("\"spaceNumber\" : " + southSpaces[i][j].SpaceNumber + ",");
                        sw.WriteLine("\"spaceType\" : \"" + southSpaces[i][j].SpaceType + "\"");

                        if (j < southSpaces[i].Count - 1)
                        {
                            sw.WriteLine("},");
                        }
                        else
                        {
                            sw.WriteLine("}");
                        }
                    }

                    if (i < southSpaces.Length - 1)
                    {
                        sw.WriteLine("},");
                    }
                    else
                    {
                        sw.WriteLine("}");
                    }
                }

                sw.WriteLine("}");
                sw.WriteLine(endCmd);
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