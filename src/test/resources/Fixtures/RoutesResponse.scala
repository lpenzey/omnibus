import akka.http.scaladsl.model.HttpResponse
import scala.concurrent.Future

trait RouteResponse {
  val input = """{
                	"bustime-response": {
                		"routes": [
                			{
                				"rt": "1",
                				"rtnm": "Bronzeville/Union Station",
                				"rtclr": "#336633",
                				"rtdd": "1"
                			},
                			{
                				"rt": "2",
                				"rtnm": "Hyde Park Express",
                				"rtclr": "#993366",
                				"rtdd": "2"
                			},
                			{
                				"rt": "3",
                				"rtnm": "King Drive",
                				"rtclr": "#009900",
                				"rtdd": "3"
                		},
                		{
                			"rt": "4",
                			"rtnm": "Cottage Grove",
                			"rtclr": "#cc3300",
                			"rtdd": "4"
                		},
                		{
                			"rt": "5",
                			"rtnm": "South Shore Night Bus",
                			"rtclr": "#996633",
                			"rtdd": "5"
                		},
                		{
                			"rt": "6",
                			"rtnm": "Jackson Park Express",
                			"rtclr": "#ff0066",
                			"rtdd": "6"
                		},
                		{
                			"rt": "7",
                			"rtnm": "Harrison",
                			"rtclr": "#666600",
                			"rtdd": "7"
                		},
                		{
                			"rt": "8",
                			"rtnm": "Halsted",
                			"rtclr": "#ff00ff",
                			"rtdd": "8"
                		},
                		{
                			"rt": "8A",
                			"rtnm": "South Halsted",
                			"rtclr": "#66cc66",
                			"rtdd": "8A"
                		},
                		{
                			"rt": "9",
                			"rtnm": "Ashland",
                			"rtclr": "#cc3366",
                			"rtdd": "9"
                		},
                		{
                			"rt": "X9",
                			"rtnm": "Ashland Express",
                			"rtclr": "#33ff00",
                			"rtdd": "X9"
                		},
                		{
                			"rt": "11",
                			"rtnm": "Lincoln",
                			"rtclr": "#ff6600",
                			"rtdd": "11"
                		},
                		{
                			"rt": "12",
                			"rtnm": "Roosevelt",
                			"rtclr": "#33ccff",
                			"rtdd": "12"
                		},
                		{
                			"rt": "J14",
                			"rtnm": "Jeffery Jump",
                			"rtclr": "#663399",
                			"rtdd": "J14"
                		},
                		{
                			"rt": "15",
                			"rtnm": "Jeffery Local",
                			"rtclr": "#ff9999",
                			"rtdd": "15"
                		},
                		{
                			"rt": "18",
                			"rtnm": "16th/18th",
                			"rtclr": "#6699ff",
                			"rtdd": "18"
                		},
                		{
                			"rt": "19",
                			"rtnm": "United Center Express",
                			"rtclr": "#78aa50",
                			"rtdd": "19"
                		},
                		{
                			"rt": "20",
                			"rtnm": "Madison",
                			"rtclr": "#336633",
                			"rtdd": "20"
                		},
                		{
                			"rt": "21",
                			"rtnm": "Cermak",
                			"rtclr": "#009900",
                			"rtdd": "21"
                		},
                		{
                			"rt": "22",
                			"rtnm": "Clark",
                			"rtclr": "#cc3300",
                			"rtdd": "22"
                		},
                		{
                			"rt": "24",
                			"rtnm": "Wentworth",
                			"rtclr": "#9900cc",
                			"rtdd": "24"
                		},
                		{
                			"rt": "26",
                			"rtnm": "South Shore Express",
                			"rtclr": "#006666",
                			"rtdd": "26"
                		},
                		{
                			"rt": "28",
                			"rtnm": "Stony Island",
                			"rtclr": "#996633",
                			"rtdd": "28"
                		},
                		{
                			"rt": "29",
                			"rtnm": "State",
                			"rtclr": "#666600",
                			"rtdd": "29"
                		},
                		{
                			"rt": "30",
                			"rtnm": "South Chicago",
                			"rtclr": "#ff00ff",
                			"rtdd": "30"
                		},
                		{
                			"rt": "31",
                			"rtnm": "31st",
                			"rtclr": "#00ffff",
                			"rtdd": "31"
                		},
                		{
                			"rt": "34",
                			"rtnm": "South Michigan",
                			"rtclr": "#cc3366",
                			"rtdd": "34"
                		},
                		{
                			"rt": "35",
                			"rtnm": "31st/35th",
                			"rtclr": "#33ff00",
                			"rtdd": "35"
                		},
                		{
                			"rt": "36",
                			"rtnm": "Broadway",
                			"rtclr": "#cc66ff",
                			"rtdd": "36"
                		},
                		{
                			"rt": "37",
                			"rtnm": "Sedgwick",
                			"rtclr": "#993366",
                			"rtdd": "37"
                		},
                		{
                			"rt": "39",
                			"rtnm": "Pershing",
                			"rtclr": "#33ccff",
                			"rtdd": "39"
                		},
                		{
                			"rt": "43",
                			"rtnm": "43rd",
                			"rtclr": "#cc9966",
                			"rtdd": "43"
                		},
                		{
                			"rt": "44",
                			"rtnm": "Wallace-Racine",
                			"rtclr": "#ff9999",
                			"rtdd": "44"
                		},
                		{
                			"rt": "47",
                			"rtnm": "47th",
                			"rtclr": "#99cc33",
                			"rtdd": "47"
                		},
                		{
                			"rt": "48",
                			"rtnm": "South Damen",
                			"rtclr": "#6699ff",
                			"rtdd": "48"
                		},
                		{
                			"rt": "49",
                			"rtnm": "Western",
                			"rtclr": "#336633",
                			"rtdd": "49"
                		},
                		{
                			"rt": "49B",
                			"rtnm": "North Western",
                			"rtclr": "#009900",
                			"rtdd": "49B"
                		},
                		{
                			"rt": "X49",
                			"rtnm": "Western Express",
                			"rtclr": "#9900cc",
                			"rtdd": "X49"
                		},
                		{
                			"rt": "50",
                			"rtnm": "Damen",
                			"rtclr": "#cc3300",
                			"rtdd": "50"
                		},
                		{
                			"rt": "51",
                			"rtnm": "51st",
                			"rtclr": "#006666",
                			"rtdd": "51"
                		},
                		{
                			"rt": "52",
                			"rtnm": "Kedzie/California",
                			"rtclr": "#996633",
                			"rtdd": "52"
                		},
                		{
                			"rt": "52A",
                			"rtnm": "South Kedzie",
                			"rtclr": "#ff0066",
                			"rtdd": "52A"
                		},
                		{
                			"rt": "53",
                			"rtnm": "Pulaski",
                			"rtclr": "#666600",
                			"rtdd": "53"
                		},
                		{
                			"rt": "53A",
                			"rtnm": "South Pulaski",
                			"rtclr": "#ff00ff",
                			"rtdd": "53A"
                		},
                		{
                			"rt": "54",
                			"rtnm": "Cicero",
                			"rtclr": "#cc3366",
                			"rtdd": "54"
                		},
                		{
                			"rt": "54A",
                			"rtnm": "North Cicero/Skokie Blvd.",
                			"rtclr": "#33ff00",
                			"rtdd": "54A"
                		},
                		{
                			"rt": "54B",
                			"rtnm": "South Cicero",
                			"rtclr": "#cc66ff",
                			"rtdd": "54B"
                		},
                		{
                			"rt": "55",
                			"rtnm": "Garfield",
                			"rtclr": "#33ccff",
                			"rtdd": "55"
                		},
                		{
                			"rt": "55A",
                			"rtnm": "55th/Austin",
                			"rtclr": "#cc9966",
                			"rtdd": "55A"
                		},
                		{
                			"rt": "55N",
                			"rtnm": "55th/Narragansett",
                			"rtclr": "#ff9999",
                			"rtdd": "55N"
                		},
                		{
                			"rt": "56",
                			"rtnm": "Milwaukee",
                			"rtclr": "#6699ff",
                			"rtdd": "56"
                		},
                		{
                			"rt": "57",
                			"rtnm": "Laramie",
                			"rtclr": "#993366",
                			"rtdd": "57"
                		},
                		{
                			"rt": "59",
                			"rtnm": "59th/61st",
                			"rtclr": "#009900",
                			"rtdd": "59"
                		},
                		{
                			"rt": "60",
                			"rtnm": "Blue Island/26th",
                			"rtclr": "#9900cc",
                			"rtdd": "60"
                		},
                		{
                			"rt": "62",
                			"rtnm": "Archer",
                			"rtclr": "#cc3300",
                			"rtdd": "62"
                		},
                		{
                			"rt": "62H",
                			"rtnm": "Archer/Harlem",
                			"rtclr": "#006666",
                			"rtdd": "62H"
                		},
                		{
                			"rt": "63",
                			"rtnm": "63rd",
                			"rtclr": "#996633",
                			"rtdd": "63"
                		},
                		{
                			"rt": "63W",
                			"rtnm": "West 63rd",
                			"rtclr": "#ff0066",
                			"rtdd": "63W"
                		},
                		{
                			"rt": "65",
                			"rtnm": "Grand",
                			"rtclr": "#ff00ff",
                			"rtdd": "65"
                		},
                		{
                			"rt": "66",
                			"rtnm": "Chicago",
                			"rtclr": "#66cc66",
                			"rtdd": "66"
                		},
                		{
                			"rt": "67",
                			"rtnm": "67th-69th-71st",
                			"rtclr": "#cc3366",
                			"rtdd": "67"
                		},
                		{
                			"rt": "68",
                			"rtnm": "Northwest Highway",
                			"rtclr": "#33ff00",
                			"rtdd": "68"
                		},
                		{
                			"rt": "70",
                			"rtnm": "Division",
                			"rtclr": "#ff6600",
                			"rtdd": "70"
                		},
                		{
                			"rt": "71",
                			"rtnm": "71st/South Shore",
                			"rtclr": "#33ccff",
                			"rtdd": "71"
                		},
                		{
                			"rt": "72",
                			"rtnm": "North",
                			"rtclr": "#cc9966",
                			"rtdd": "72"
                		},
                		{
                			"rt": "73",
                			"rtnm": "Armitage",
                			"rtclr": "#ff9999",
                			"rtdd": "73"
                		},
                		{
                			"rt": "74",
                			"rtnm": "Fullerton",
                			"rtclr": "#99cc33",
                			"rtdd": "74"
                		},
                		{
                			"rt": "75",
                			"rtnm": "74th-75th",
                			"rtclr": "#6699ff",
                			"rtdd": "75"
                		},
                		{
                			"rt": "76",
                			"rtnm": "Diversey",
                			"rtclr": "#336633",
                			"rtdd": "76"
                		},
                		{
                			"rt": "77",
                			"rtnm": "Belmont",
                			"rtclr": "#993366",
                			"rtdd": "77"
                		},
                		{
                			"rt": "78",
                			"rtnm": "Montrose",
                			"rtclr": "#009900",
                			"rtdd": "78"
                		},
                		{
                			"rt": "79",
                			"rtnm": "79th",
                			"rtclr": "#9900cc",
                			"rtdd": "79"
                		},
                		{
                			"rt": "80",
                			"rtnm": "Irving Park",
                			"rtclr": "#cc3300",
                			"rtdd": "80"
                		},
                		{
                			"rt": "81",
                			"rtnm": "Lawrence",
                			"rtclr": "#996633",
                			"rtdd": "81"
                		},
                		{
                			"rt": "81W",
                			"rtnm": "West Lawrence",
                			"rtclr": "#ff0066",
                			"rtdd": "81W"
                		},
                		{
                			"rt": "82",
                			"rtnm": "Kimball-Homan",
                			"rtclr": "#666600",
                			"rtdd": "82"
                		},
                		{
                			"rt": "84",
                			"rtnm": "Peterson",
                			"rtclr": "#ff00ff",
                			"rtdd": "84"
                		},
                		{
                			"rt": "85",
                			"rtnm": "Central",
                			"rtclr": "#66cc66",
                			"rtdd": "85"
                		},
                		{
                			"rt": "85A",
                			"rtnm": "North Central",
                			"rtclr": "#cc3366",
                			"rtdd": "85A"
                		},
                		{
                			"rt": "86",
                			"rtnm": "Narragansett/Ridgeland",
                			"rtclr": "#33ff00",
                			"rtdd": "86"
                		},
                		{
                			"rt": "87",
                			"rtnm": "87th",
                			"rtclr": "#cc66ff",
                			"rtdd": "87"
                		},
                		{
                			"rt": "88",
                			"rtnm": "Higgins",
                			"rtclr": "#ff6600",
                			"rtdd": "88"
                		},
                		{
                			"rt": "90",
                			"rtnm": "Harlem",
                			"rtclr": "#33ccff",
                			"rtdd": "90"
                		},
                		{
                			"rt": "91",
                			"rtnm": "Austin",
                			"rtclr": "#ff9999",
                			"rtdd": "91"
                		},
                		{
                			"rt": "92",
                			"rtnm": "Foster",
                			"rtclr": "#99cc33",
                			"rtdd": "92"
                		},
                		{
                			"rt": "93",
                			"rtnm": "California/Dodge",
                			"rtclr": "#6699ff",
                			"rtdd": "93"
                		},
                		{
                			"rt": "94",
                			"rtnm": "South California",
                			"rtclr": "#336633",
                			"rtdd": "94"
                		},
                		{
                			"rt": "95",
                			"rtnm": "95th",
                			"rtclr": "#00ffff",
                			"rtdd": "95"
                		},
                		{
                			"rt": "96",
                			"rtnm": "Lunt",
                			"rtclr": "#9900cc",
                			"rtdd": "96"
                		},
                		{
                			"rt": "97",
                			"rtnm": "Skokie",
                			"rtclr": "#cc3300",
                			"rtdd": "97"
                		},
                		{
                			"rt": "X98",
                			"rtnm": "Avon Express",
                			"rtclr": "#006666",
                			"rtdd": "X98"
                		},
                		{
                			"rt": "100",
                			"rtnm": "Jeffery Manor Express",
                			"rtclr": "#996633",
                			"rtdd": "100"
                		},
                		{
                			"rt": "103",
                			"rtnm": "West 103rd",
                			"rtclr": "#ff0066",
                			"rtdd": "103"
                		},
                		{
                			"rt": "106",
                			"rtnm": "East 103rd",
                			"rtclr": "#666600",
                			"rtdd": "106"
                		},
                		{
                			"rt": "108",
                			"rtnm": "Halsted/95th",
                			"rtclr": "#ff00ff",
                			"rtdd": "108"
                		},
                		{
                			"rt": "111",
                			"rtnm": "111th/King Drive",
                			"rtclr": "#66cc66",
                			"rtdd": "111"
                		},
                		{
                			"rt": "111A",
                			"rtnm": "Pullman Shuttle",
                			"rtclr": "#ff3333",
                			"rtdd": "111A"
                		},
                		{
                			"rt": "112",
                			"rtnm": "Vincennes/111th",
                			"rtclr": "#cc3366",
                			"rtdd": "112"
                		},
                		{
                			"rt": "115",
                			"rtnm": "Pullman/115th",
                			"rtclr": "#996633",
                			"rtdd": "115"
                		},
                		{
                			"rt": "119",
                			"rtnm": "Michigan/119th",
                			"rtclr": "#33ff00",
                			"rtdd": "119"
                		},
                		{
                			"rt": "120",
                			"rtnm": "Ogilvie/Streeterville Express",
                			"rtclr": "#cc66ff",
                			"rtdd": "120"
                		},
                		{
                			"rt": "121",
                			"rtnm": "Union/Streeterville Express",
                			"rtclr": "#ff6600",
                			"rtdd": "121"
                		},
                		{
                			"rt": "124",
                			"rtnm": "Navy Pier",
                			"rtclr": "#ff9999",
                			"rtdd": "124"
                		},
                		{
                			"rt": "125",
                			"rtnm": "Water Tower Express",
                			"rtclr": "#99cc33",
                			"rtdd": "125"
                		},
                		{
                			"rt": "126",
                			"rtnm": "Jackson",
                			"rtclr": "#6699ff",
                			"rtdd": "126"
                		},
                		{
                			"rt": "134",
                			"rtnm": "Stockton/LaSalle Express",
                			"rtclr": "#009900",
                			"rtdd": "134"
                		},
                		{
                			"rt": "135",
                			"rtnm": "Clarendon/LaSalle Express",
                			"rtclr": "#9900cc",
                			"rtdd": "135"
                		},
                		{
                			"rt": "136",
                			"rtnm": "Sheridan/LaSalle Express",
                			"rtclr": "#cc3300",
                			"rtdd": "136"
                		},
                		{
                			"rt": "143",
                			"rtnm": "Stockton/Michigan Express",
                			"rtclr": "#006666",
                			"rtdd": "143"
                		},
                		{
                			"rt": "146",
                			"rtnm": "Inner Drive/Michigan Express",
                			"rtclr": "#666600",
                			"rtdd": "146"
                		},
                		{
                			"rt": "147",
                			"rtnm": "Outer Drive Express",
                			"rtclr": "#ff00ff",
                			"rtdd": "147"
                		},
                		{
                			"rt": "148",
                			"rtnm": "Clarendon/Michigan Express",
                			"rtclr": "#66cc66",
                			"rtdd": "148"
                		},
                		{
                			"rt": "151",
                			"rtnm": "Sheridan",
                			"rtclr": "#cc3366",
                			"rtdd": "151"
                		},
                		{
                			"rt": "152",
                			"rtnm": "Addison",
                			"rtclr": "#33ff00",
                			"rtdd": "152"
                		},
                		{
                			"rt": "155",
                			"rtnm": "Devon",
                			"rtclr": "#cc66ff",
                			"rtdd": "155"
                		},
                		{
                			"rt": "156",
                			"rtnm": "LaSalle",
                			"rtclr": "#ff6600",
                			"rtdd": "156"
                		},
                		{
                			"rt": "157",
                			"rtnm": "Streeterville/Taylor",
                			"rtclr": "#33ccff",
                			"rtdd": "157"
                		},
                		{
                			"rt": "165",
                			"rtnm": "West 65th",
                			"rtclr": "#cc9966",
                			"rtdd": "165"
                		},
                		{
                			"rt": "169",
                			"rtnm": "69th-UPS Express",
                			"rtclr": "#99cc33",
                			"rtdd": "169"
                		},
                		{
                			"rt": "171",
                			"rtnm": "U. of Chicago/Hyde Park",
                			"rtclr": "#336633",
                			"rtdd": "171"
                		},
                		{
                			"rt": "172",
                			"rtnm": "U. of Chicago/Kenwood",
                			"rtclr": "#993366",
                			"rtdd": "172"
                		},
                		{
                			"rt": "192",
                			"rtnm": "U. of Chicago Hospitals Express",
                			"rtclr": "#cc3300",
                			"rtdd": "192"
                		},
                		{
                			"rt": "201",
                			"rtnm": "Central/Ridge",
                			"rtclr": "#996633",
                			"rtdd": "201"
                		},
                		{
                			"rt": "206",
                			"rtnm": "Evanston Circulator",
                			"rtclr": "#666600",
                			"rtdd": "206"
                		}
                	]
                }
                |}{
                "bustime-response": {
                	"routes": [
                		{
                			"rt": "1",
                			"rtnm": "Bronzeville/Union Station",
                			"rtclr": "#336633",
                			"rtdd": "1"
                		},
                		{
                			"rt": "2",
                			"rtnm": "Hyde Park Express",
                			"rtclr": "#993366",
                			"rtdd": "2"
                		},
                		{
                			"rt": "3",
                			"rtnm": "King Drive",
                			"rtclr": "#009900",
                			"rtdd": "3"
                		},
                		{
                			"rt": "4",
                			"rtnm": "Cottage Grove",
                			"rtclr": "#cc3300",
                			"rtdd": "4"
                		},
                		{
                			"rt": "5",
                			"rtnm": "South Shore Night Bus",
                			"rtclr": "#996633",
                			"rtdd": "5"
                		},
                		{
                			"rt": "6",
                			"rtnm": "Jackson Park Express",
                			"rtclr": "#ff0066",
                			"rtdd": "6"
                		},
                		{
                			"rt": "7",
                			"rtnm": "Harrison",
                			"rtclr": "#666600",
                			"rtdd": "7"
                		},
                		{
                			"rt": "8",
                			"rtnm": "Halsted",
                			"rtclr": "#ff00ff",
                			"rtdd": "8"
                		},
                		{
                			"rt": "8A",
                			"rtnm": "South Halsted",
                			"rtclr": "#66cc66",
                			"rtdd": "8A"
                		},
                		{
                			"rt": "9",
                			"rtnm": "Ashland",
                			"rtclr": "#cc3366",
                			"rtdd": "9"
                		},
                		{
                			"rt": "X9",
                			"rtnm": "Ashland Express",
                			"rtclr": "#33ff00",
                			"rtdd": "X9"
                		},
                		{
                			"rt": "11",
                			"rtnm": "Lincoln",
                			"rtclr": "#ff6600",
                			"rtdd": "11"
                		},
                		{
                			"rt": "12",
                			"rtnm": "Roosevelt",
                			"rtclr": "#33ccff",
                			"rtdd": "12"
                		},
                		{
                			"rt": "J14",
                			"rtnm": "Jeffery Jump",
                			"rtclr": "#663399",
                			"rtdd": "J14"
                		},
                		{
                			"rt": "15",
                			"rtnm": "Jeffery Local",
                			"rtclr": "#ff9999",
                			"rtdd": "15"
                		},
                		{
                			"rt": "18",
                			"rtnm": "16th/18th",
                			"rtclr": "#6699ff",
                			"rtdd": "18"
                		},
                		{
                			"rt": "19",
                			"rtnm": "United Center Express",
                			"rtclr": "#78aa50",
                			"rtdd": "19"
                		},
                		{
                			"rt": "20",
                			"rtnm": "Madison",
                			"rtclr": "#336633",
                			"rtdd": "20"
                		},
                		{
                			"rt": "21",
                			"rtnm": "Cermak",
                			"rtclr": "#009900",
                			"rtdd": "21"
                		},
                		{
                			"rt": "22",
                			"rtnm": "Clark",
                			"rtclr": "#cc3300",
                			"rtdd": "22"
                		},
                		{
                			"rt": "24",
                			"rtnm": "Wentworth",
                			"rtclr": "#9900cc",
                			"rtdd": "24"
                		},
                		{
                			"rt": "26",
                			"rtnm": "South Shore Express",
                			"rtclr": "#006666",
                			"rtdd": "26"
                		},
                		{
                			"rt": "28",
                			"rtnm": "Stony Island",
                			"rtclr": "#996633",
                			"rtdd": "28"
                		},
                		{
                			"rt": "29",
                			"rtnm": "State",
                			"rtclr": "#666600",
                			"rtdd": "29"
                		},
                		{
                			"rt": "30",
                			"rtnm": "South Chicago",
                			"rtclr": "#ff00ff",
                			"rtdd": "30"
                		},
                		{
                			"rt": "31",
                			"rtnm": "31st",
                			"rtclr": "#00ffff",
                			"rtdd": "31"
                		},
                		{
                			"rt": "34",
                			"rtnm": "South Michigan",
                			"rtclr": "#cc3366",
                			"rtdd": "34"
                		},
                		{
                			"rt": "35",
                			"rtnm": "31st/35th",
                			"rtclr": "#33ff00",
                			"rtdd": "35"
                		},
                		{
                			"rt": "36",
                			"rtnm": "Broadway",
                			"rtclr": "#cc66ff",
                			"rtdd": "36"
                		},
                		{
                			"rt": "37",
                			"rtnm": "Sedgwick",
                			"rtclr": "#993366",
                			"rtdd": "37"
                		},
                		{
                			"rt": "39",
                			"rtnm": "Pershing",
                			"rtclr": "#33ccff",
                			"rtdd": "39"
                		},
                		{
                			"rt": "43",
                			"rtnm": "43rd",
                			"rtclr": "#cc9966",
                			"rtdd": "43"
                		},
                		{
                			"rt": "44",
                			"rtnm": "Wallace-Racine",
                			"rtclr": "#ff9999",
                			"rtdd": "44"
                		},
                		{
                			"rt": "47",
                			"rtnm": "47th",
                			"rtclr": "#99cc33",
                			"rtdd": "47"
                		},
                		{
                			"rt": "48",
                			"rtnm": "South Damen",
                			"rtclr": "#6699ff",
                			"rtdd": "48"
                		},
                		{
                			"rt": "49",
                			"rtnm": "Western",
                			"rtclr": "#336633",
                			"rtdd": "49"
                		},
                		{
                			"rt": "49B",
                			"rtnm": "North Western",
                			"rtclr": "#009900",
                			"rtdd": "49B"
                		},
                		{
                			"rt": "X49",
                			"rtnm": "Western Express",
                			"rtclr": "#9900cc",
                			"rtdd": "X49"
                		},
                		{
                			"rt": "50",
                			"rtnm": "Damen",
                			"rtclr": "#cc3300",
                			"rtdd": "50"
                		},
                		{
                			"rt": "51",
                			"rtnm": "51st",
                			"rtclr": "#006666",
                			"rtdd": "51"
                		},
                		{
                			"rt": "52",
                			"rtnm": "Kedzie/California",
                			"rtclr": "#996633",
                			"rtdd": "52"
                		},
                		{
                			"rt": "52A",
                			"rtnm": "South Kedzie",
                			"rtclr": "#ff0066",
                			"rtdd": "52A"
                		},
                		{
                			"rt": "53",
                			"rtnm": "Pulaski",
                			"rtclr": "#666600",
                			"rtdd": "53"
                		},
                		{
                			"rt": "53A",
                			"rtnm": "South Pulaski",
                			"rtclr": "#ff00ff",
                			"rtdd": "53A"
                		},
                		{
                			"rt": "54",
                			"rtnm": "Cicero",
                			"rtclr": "#cc3366",
                			"rtdd": "54"
                		},
                		{
                			"rt": "54A",
                			"rtnm": "North Cicero/Skokie Blvd.",
                			"rtclr": "#33ff00",
                			"rtdd": "54A"
                		},
                		{
                			"rt": "54B",
                			"rtnm": "South Cicero",
                			"rtclr": "#cc66ff",
                			"rtdd": "54B"
                		},
                		{
                			"rt": "55",
                			"rtnm": "Garfield",
                			"rtclr": "#33ccff",
                			"rtdd": "55"
                		},
                		{
                			"rt": "55A",
                			"rtnm": "55th/Austin",
                			"rtclr": "#cc9966",
                			"rtdd": "55A"
                		},
                		{
                			"rt": "55N",
                			"rtnm": "55th/Narragansett",
                			"rtclr": "#ff9999",
                			"rtdd": "55N"
                		},
                		{
                			"rt": "56",
                			"rtnm": "Milwaukee",
                			"rtclr": "#6699ff",
                			"rtdd": "56"
                		},
                		{
                			"rt": "57",
                			"rtnm": "Laramie",
                			"rtclr": "#993366",
                			"rtdd": "57"
                		},
                		{
                			"rt": "59",
                			"rtnm": "59th/61st",
                			"rtclr": "#009900",
                			"rtdd": "59"
                		},
                		{
                			"rt": "60",
                			"rtnm": "Blue Island/26th",
                			"rtclr": "#9900cc",
                			"rtdd": "60"
                		},
                		{
                			"rt": "62",
                			"rtnm": "Archer",
                			"rtclr": "#cc3300",
                			"rtdd": "62"
                		},
                		{
                			"rt": "62H",
                			"rtnm": "Archer/Harlem",
                			"rtclr": "#006666",
                			"rtdd": "62H"
                		},
                		{
                			"rt": "63",
                			"rtnm": "63rd",
                			"rtclr": "#996633",
                			"rtdd": "63"
                		},
                		{
                			"rt": "63W",
                			"rtnm": "West 63rd",
                			"rtclr": "#ff0066",
                			"rtdd": "63W"
                		},
                		{
                			"rt": "65",
                			"rtnm": "Grand",
                			"rtclr": "#ff00ff",
                			"rtdd": "65"
                		},
                		{
                			"rt": "66",
                			"rtnm": "Chicago",
                			"rtclr": "#66cc66",
                			"rtdd": "66"
                		},
                		{
                			"rt": "67",
                			"rtnm": "67th-69th-71st",
                			"rtclr": "#cc3366",
                			"rtdd": "67"
                		},
                		{
                			"rt": "68",
                			"rtnm": "Northwest Highway",
                			"rtclr": "#33ff00",
                			"rtdd": "68"
                		},
                		{
                			"rt": "70",
                			"rtnm": "Division",
                			"rtclr": "#ff6600",
                			"rtdd": "70"
                		},
                		{
                			"rt": "71",
                			"rtnm": "71st/South Shore",
                			"rtclr": "#33ccff",
                			"rtdd": "71"
                		},
                		{
                			"rt": "72",
                			"rtnm": "North",
                			"rtclr": "#cc9966",
                			"rtdd": "72"
                		},
                		{
                			"rt": "73",
                			"rtnm": "Armitage",
                			"rtclr": "#ff9999",
                			"rtdd": "73"
                		},
                		{
                			"rt": "74",
                			"rtnm": "Fullerton",
                			"rtclr": "#99cc33",
                			"rtdd": "74"
                		},
                		{
                			"rt": "75",
                			"rtnm": "74th-75th",
                			"rtclr": "#6699ff",
                			"rtdd": "75"
                		},
                		{
                			"rt": "76",
                			"rtnm": "Diversey",
                			"rtclr": "#336633",
                			"rtdd": "76"
                		},
                		{
                			"rt": "77",
                			"rtnm": "Belmont",
                			"rtclr": "#993366",
                			"rtdd": "77"
                		},
                		{
                			"rt": "78",
                			"rtnm": "Montrose",
                			"rtclr": "#009900",
                			"rtdd": "78"
                		},
                		{
                			"rt": "79",
                			"rtnm": "79th",
                			"rtclr": "#9900cc",
                			"rtdd": "79"
                		},
                		{
                			"rt": "80",
                			"rtnm": "Irving Park",
                			"rtclr": "#cc3300",
                			"rtdd": "80"
                		},
                		{
                			"rt": "81",
                			"rtnm": "Lawrence",
                			"rtclr": "#996633",
                			"rtdd": "81"
                		},
                		{
                			"rt": "81W",
                			"rtnm": "West Lawrence",
                			"rtclr": "#ff0066",
                			"rtdd": "81W"
                		},
                		{
                			"rt": "82",
                			"rtnm": "Kimball-Homan",
                			"rtclr": "#666600",
                			"rtdd": "82"
                		},
                		{
                			"rt": "84",
                			"rtnm": "Peterson",
                			"rtclr": "#ff00ff",
                			"rtdd": "84"
                		},
                		{
                			"rt": "85",
                			"rtnm": "Central",
                			"rtclr": "#66cc66",
                			"rtdd": "85"
                		},
                		{
                			"rt": "85A",
                			"rtnm": "North Central",
                			"rtclr": "#cc3366",
                			"rtdd": "85A"
                		},
                		{
                			"rt": "86",
                			"rtnm": "Narragansett/Ridgeland",
                			"rtclr": "#33ff00",
                			"rtdd": "86"
                		},
                		{
                			"rt": "87",
                			"rtnm": "87th",
                			"rtclr": "#cc66ff",
                			"rtdd": "87"
                		},
                		{
                			"rt": "88",
                			"rtnm": "Higgins",
                			"rtclr": "#ff6600",
                			"rtdd": "88"
                		},
                		{
                			"rt": "90",
                			"rtnm": "Harlem",
                			"rtclr": "#33ccff",
                			"rtdd": "90"
                		},
                		{
                			"rt": "91",
                			"rtnm": "Austin",
                			"rtclr": "#ff9999",
                			"rtdd": "91"
                		},
                		{
                			"rt": "92",
                			"rtnm": "Foster",
                			"rtclr": "#99cc33",
                			"rtdd": "92"
                		},
                		{
                			"rt": "93",
                			"rtnm": "California/Dodge",
                			"rtclr": "#6699ff",
                			"rtdd": "93"
                		},
                		{
                			"rt": "94",
                			"rtnm": "South California",
                			"rtclr": "#336633",
                			"rtdd": "94"
                		},
                		{
                			"rt": "95",
                			"rtnm": "95th",
                			"rtclr": "#00ffff",
                			"rtdd": "95"
                		},
                		{
                			"rt": "96",
                			"rtnm": "Lunt",
                			"rtclr": "#9900cc",
                			"rtdd": "96"
                		},
                		{
                			"rt": "97",
                			"rtnm": "Skokie",
                			"rtclr": "#cc3300",
                			"rtdd": "97"
                		},
                		{
                			"rt": "X98",
                			"rtnm": "Avon Express",
                			"rtclr": "#006666",
                			"rtdd": "X98"
                		},
                		{
                			"rt": "100",
                			"rtnm": "Jeffery Manor Express",
                			"rtclr": "#996633",
                			"rtdd": "100"
                		},
                		{
                			"rt": "103",
                			"rtnm": "West 103rd",
                			"rtclr": "#ff0066",
                			"rtdd": "103"
                		},
                		{
                			"rt": "106",
                			"rtnm": "East 103rd",
                			"rtclr": "#666600",
                			"rtdd": "106"
                		},
                		{
                			"rt": "108",
                			"rtnm": "Halsted/95th",
                			"rtclr": "#ff00ff",
                			"rtdd": "108"
                		},
                		{
                			"rt": "111",
                			"rtnm": "111th/King Drive",
                			"rtclr": "#66cc66",
                			"rtdd": "111"
                		},
                		{
                			"rt": "111A",
                			"rtnm": "Pullman Shuttle",
                			"rtclr": "#ff3333",
                			"rtdd": "111A"
                		},
                		{
                			"rt": "112",
                			"rtnm": "Vincennes/111th",
                			"rtclr": "#cc3366",
                			"rtdd": "112"
                		},
                		{
                			"rt": "115",
                			"rtnm": "Pullman/115th",
                			"rtclr": "#996633",
                			"rtdd": "115"
                		},
                		{
                			"rt": "119",
                			"rtnm": "Michigan/119th",
                			"rtclr": "#33ff00",
                			"rtdd": "119"
                		},
                		{
                			"rt": "120",
                			"rtnm": "Ogilvie/Streeterville Express",
                			"rtclr": "#cc66ff",
                			"rtdd": "120"
                		},
                		{
                			"rt": "121",
                			"rtnm": "Union/Streeterville Express",
                			"rtclr": "#ff6600",
                			"rtdd": "121"
                		},
                		{
                			"rt": "124",
                			"rtnm": "Navy Pier",
                			"rtclr": "#ff9999",
                			"rtdd": "124"
                		},
                		{
                			"rt": "125",
                			"rtnm": "Water Tower Express",
                			"rtclr": "#99cc33",
                			"rtdd": "125"
                		},
                		{
                			"rt": "126",
                			"rtnm": "Jackson",
                			"rtclr": "#6699ff",
                			"rtdd": "126"
                		},
                		{
                			"rt": "134",
                			"rtnm": "Stockton/LaSalle Express",
                			"rtclr": "#009900",
                			"rtdd": "134"
                		},
                		{
                			"rt": "135",
                			"rtnm": "Clarendon/LaSalle Express",
                			"rtclr": "#9900cc",
                			"rtdd": "135"
                		},
                		{
                			"rt": "136",
                			"rtnm": "Sheridan/LaSalle Express",
                			"rtclr": "#cc3300",
                			"rtdd": "136"
                		},
                		{
                			"rt": "143",
                			"rtnm": "Stockton/Michigan Express",
                			"rtclr": "#006666",
                			"rtdd": "143"
                		},
                		{
                			"rt": "146",
                			"rtnm": "Inner Drive/Michigan Express",
                			"rtclr": "#666600",
                			"rtdd": "146"
                		},
                		{
                			"rt": "147",
                			"rtnm": "Outer Drive Express",
                			"rtclr": "#ff00ff",
                			"rtdd": "147"
                		},
                		{
                			"rt": "148",
                			"rtnm": "Clarendon/Michigan Express",
                			"rtclr": "#66cc66",
                			"rtdd": "148"
                		},
                		{
                			"rt": "151",
                			"rtnm": "Sheridan",
                			"rtclr": "#cc3366",
                			"rtdd": "151"
                		},
                		{
                			"rt": "152",
                			"rtnm": "Addison",
                			"rtclr": "#33ff00",
                			"rtdd": "152"
                		},
                		{
                			"rt": "155",
                			"rtnm": "Devon",
                			"rtclr": "#cc66ff",
                			"rtdd": "155"
                		},
                		{
                			"rt": "156",
                			"rtnm": "LaSalle",
                			"rtclr": "#ff6600",
                			"rtdd": "156"
                		},
                		{
                			"rt": "157",
                			"rtnm": "Streeterville/Taylor",
                			"rtclr": "#33ccff",
                			"rtdd": "157"
                		},
                		{
                			"rt": "165",
                			"rtnm": "West 65th",
                			"rtclr": "#cc9966",
                			"rtdd": "165"
                		},
                		{
                			"rt": "169",
                			"rtnm": "69th-UPS Express",
                			"rtclr": "#99cc33",
                			"rtdd": "169"
                		},
                		{
                			"rt": "171",
                			"rtnm": "U. of Chicago/Hyde Park",
                			"rtclr": "#336633",
                			"rtdd": "171"
                		},
                		{
                			"rt": "172",
                			"rtnm": "U. of Chicago/Kenwood",
                			"rtclr": "#993366",
                			"rtdd": "172"
                		},
                		{
                			"rt": "192",
                			"rtnm": "U. of Chicago Hospitals Express",
                			"rtclr": "#cc3300",
                			"rtdd": "192"
                		},
                		{
                			"rt": "201",
                			"rtnm": "Central/Ridge",
                			"rtclr": "#996633",
                			"rtdd": "201"
                		},
                		{
                			"rt": "206",
                			"rtnm": "Evanston Circulator",
                			"rtclr": "#666600",
                			"rtdd": "206"
                		}
                	]
                }
                }"""
}