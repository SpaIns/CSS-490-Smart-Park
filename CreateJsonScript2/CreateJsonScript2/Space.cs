using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CreateJsonScript2
{
    class Space
    {
        public int SpaceNumber { get; private set; }
        public string SpaceType { get; private set; }
        public bool IsAvailable { get; private set; }
        public string Garage { get; private set; }
        public int Floor { get; private set; }
        

        public Space(int spaceNumber, string spaceType, bool isAvailable, string garage, int floor)
        {
            this.SpaceNumber = spaceNumber;
            this.SpaceType = spaceType;
            this.IsAvailable = isAvailable;
            this.Garage = garage;
            this.Floor = floor;
        }

    }
}
