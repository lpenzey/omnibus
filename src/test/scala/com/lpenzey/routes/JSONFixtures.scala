package com.lpenzey.routes

object JSONFixtures {
  val key = System.getenv("BUS_TRACKER_API_KEY")
  val routesUri = "http://www.ctabustracker.com/bustime/api/v2/getroutes?&key=" + key + "&format=json"
  val directionsUri = "http://www.ctabustracker.com/bustime/api/v2/getstops?&key=" + key + "&format=json"
  val stopsUri = "http://www.ctabustracker.com/bustime/api/v2/getroutes?&key=" + key + "&format=json"
  val predictionsUri = "http://www.ctabustracker.com/bustime/api/v2/getroutes?&key=" + key + "&format=json"

  val routes = """{
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
  val directions = """{
                     	"bustime-response": {
                     		"directions": [
                     			{
                     				"dir": "Eastbound"
                     			},
                     			{
                     				"dir": "Westbound"
                     			}
                     		]
                     	}
                     }"""
  val stops = """{
                	"bustime-response": {
                		"stops": [
                			{
                				"stpid": "2032",
                				"stpnm": "311 W Division",
                				"lat": 41.903858000001,
                				"lon": -87.636123999999
                			},
                			{
                				"stpid": "1983",
                				"stpnm": "4659 W Division",
                				"lat": 41.902357000001,
                				"lon": -87.743576999999
                			},
                			{
                				"stpid": "2037",
                				"stpnm": "Dearborn & Maple",
                				"lat": 41.901960000001,
                				"lon": -87.629982999999
                			},
                			{
                				"stpid": "2014",
                				"stpnm": "Division & Ashland (Blue Line)",
                				"lat": 41.903255,
                				"lon": -87.667837999999
                			},
                			{
                				"stpid": "1970",
                				"stpnm": "Division & Austin Terminal",
                				"lat": 41.901807999999,
                				"lon": -87.775111999998
                			},
                			{
                				"stpid": "15014",
                				"stpnm": "Division & California",
                				"lat": 41.902921999999,
                				"lon": -87.696513000001
                			},
                			{
                				"stpid": "2005",
                				"stpnm": "Division & Campbell",
                				"lat": 41.902945,
                				"lon": -87.689583000001
                			},
                			{
                				"stpid": "17975",
                				"stpnm": "Division & Central",
                				"lat": 41.902126999999,
                				"lon": -87.765273000001
                			},
                			{
                				"stpid": "17976",
                				"stpnm": "Division & Central Park",
                				"lat": 41.902715,
                				"lon": -87.716063
                			},
                			{
                				"stpid": "2022",
                				"stpnm": "Division & Cherry",
                				"lat": 41.903588,
                				"lon": -87.654265000001
                			},
                			{
                				"stpid": "15191",
                				"stpnm": "Division & Cicero",
                				"lat": 41.902347999999,
                				"lon": -87.745468
                			},
                			{
                				"stpid": "5370",
                				"stpnm": "Division & Clark (Red Line)",
                				"lat": 41.903914999999,
                				"lon": -87.631186999998
                			},
                			{
                				"stpid": "15372",
                				"stpnm": "Division & Cleaver",
                				"lat": 41.903343,
                				"lon": -87.663595000001
                			},
                			{
                				"stpid": "18156",
                				"stpnm": "Division & Clybourn",
                				"lat": 41.903714999999,
                				"lon": -87.63978
                			},
                			{
                				"stpid": "14776",
                				"stpnm": "Division & Damen",
                				"lat": 41.903143000001,
                				"lon": -87.676834999999
                			},
                			{
                				"stpid": "2020",
                				"stpnm": "Division & Elston",
                				"lat": 41.90343,
                				"lon": -87.659160000001
                			},
                			{
                				"stpid": "2001",
                				"stpnm": "Division & Francisco",
                				"lat": 41.902881999999,
                				"lon": -87.699485000001
                			},
                			{
                				"stpid": "1993",
                				"stpnm": "Division & Grand/Monticello",
                				"lat": 41.902674999999,
                				"lon": -87.71797
                			},
                			{
                				"stpid": "17184",
                				"stpnm": "Division & Halsted",
                				"lat": 41.903613000001,
                				"lon": -87.647780000001
                			},
                			{
                				"stpid": "1992",
                				"stpnm": "Division & Hamlin",
                				"lat": 41.902652000001,
                				"lon": -87.721577999999
                			},
                			{
                				"stpid": "17977",
                				"stpnm": "Division & Homan",
                				"lat": 41.902754999999,
                				"lon": -87.711214999999
                			},
                			{
                				"stpid": "15374",
                				"stpnm": "Division & Hooker",
                				"lat": 41.903566000001,
                				"lon": -87.650792999999
                			},
                			{
                				"stpid": "2009",
                				"stpnm": "Division & Hoyne",
                				"lat": 41.903097000001,
                				"lon": -87.679823000001
                			},
                			{
                				"stpid": "15829",
                				"stpnm": "Division & Humboldt",
                				"lat": 41.902865,
                				"lon": -87.702680000001
                			},
                			{
                				"stpid": "1989",
                				"stpnm": "Division & Karlov",
                				"lat": 41.902554999999,
                				"lon": -87.728921999999
                			},
                			{
                				"stpid": "14570",
                				"stpnm": "Division & Kedzie",
                				"lat": 41.902802,
                				"lon": -87.706288
                			},
                			{
                				"stpid": "15805",
                				"stpnm": "Division & Keeler",
                				"lat": 41.902562999999,
                				"lon": -87.730781999999
                			},
                			{
                				"stpid": "1985",
                				"stpnm": "Division & Kilbourn",
                				"lat": 41.90242,
                				"lon": -87.738713000001
                			},
                			{
                				"stpid": "1987",
                				"stpnm": "Division & Kildare",
                				"lat": 41.902507999999,
                				"lon": -87.733817000001
                			},
                			{
                				"stpid": "1984",
                				"stpnm": "Division & Kolmar",
                				"lat": 41.902405000001,
                				"lon": -87.740094999999
                			},
                			{
                				"stpid": "1986",
                				"stpnm": "Division & Kostner",
                				"lat": 41.90246,
                				"lon": -87.736249999999
                			},
                			{
                				"stpid": "1981",
                				"stpnm": "Division & Lamon",
                				"lat": 41.902301999999,
                				"lon": -87.748534999999
                			},
                			{
                				"stpid": "17186",
                				"stpnm": "Division & Laramie",
                				"lat": 41.902206999999,
                				"lon": -87.75553
                			},
                			{
                				"stpid": "2028",
                				"stpnm": "Division & Larrabee",
                				"lat": 41.903712999999,
                				"lon": -87.643325999998
                			},
                			{
                				"stpid": "2034",
                				"stpnm": "Division & Lasalle",
                				"lat": 41.903915000001,
                				"lon": -87.633285
                			},
                			{
                				"stpid": "15463",
                				"stpnm": "Division & Lavergne",
                				"lat": 41.90223,
                				"lon": -87.750570000001
                			},
                			{
                				"stpid": "14565",
                				"stpnm": "Division & Leavitt",
                				"lat": 41.903057,
                				"lon": -87.681746999999
                			},
                			{
                				"stpid": "1979",
                				"stpnm": "Division & Leclaire",
                				"lat": 41.902246999999,
                				"lon": -87.753446999999
                			},
                			{
                				"stpid": "1977",
                				"stpnm": "Division & Lockwood",
                				"lat": 41.902198,
                				"lon": -87.758375
                			},
                			{
                				"stpid": "1976",
                				"stpnm": "Division & Long",
                				"lat": 41.902166999999,
                				"lon": -87.761059999999
                			},
                			{
                				"stpid": "1971",
                				"stpnm": "Division & Mayfield",
                				"lat": 41.901958000001,
                				"lon": -87.773147
                			},
                			{
                				"stpid": "1972",
                				"stpnm": "Division & Menard",
                				"lat": 41.902000000001,
                				"lon": -87.770653000001
                			},
                			{
                				"stpid": "16081",
                				"stpnm": "Division & Milwaukee (Blue Line)",
                				"lat": 41.903317999999,
                				"lon": -87.665914999999
                			},
                			{
                				"stpid": "18162",
                				"stpnm": "Division & Oakley",
                				"lat": 41.90303,
                				"lon": -87.684267999999
                			},
                			{
                				"stpid": "2013",
                				"stpnm": "Division & Paulina",
                				"lat": 41.903255000001,
                				"lon": -87.670112000001
                			},
                			{
                				"stpid": "1975",
                				"stpnm": "Division & Pine",
                				"lat": 41.902142999999,
                				"lon": -87.763953000001
                			},
                			{
                				"stpid": "1990",
                				"stpnm": "Division & Pulaski",
                				"lat": 41.902612,
                				"lon": -87.726442000001
                			},
                			{
                				"stpid": "2000",
                				"stpnm": "Division & Richmond",
                				"lat": 41.902872999999,
                				"lon": -87.700772999998
                			},
                			{
                				"stpid": "2004",
                				"stpnm": "Division & Rockwell",
                				"lat": 41.902922,
                				"lon": -87.692062
                			},
                			{
                				"stpid": "1997",
                				"stpnm": "Division & Spaulding",
                				"lat": 41.902778,
                				"lon": -87.709593
                			},
                			{
                				"stpid": "1991",
                				"stpnm": "Division & Springfield",
                				"lat": 41.902651999999,
                				"lon": -87.724010000001
                			},
                			{
                				"stpid": "1973",
                				"stpnm": "Division & Waller",
                				"lat": 41.902078,
                				"lon": -87.768197000001
                			},
                			{
                				"stpid": "2003",
                				"stpnm": "Division & Washtenaw",
                				"lat": 41.902904999999,
                				"lon": -87.69459
                			},
                			{
                				"stpid": "2033",
                				"stpnm": "Division & Wells",
                				"lat": 41.903889000001,
                				"lon": -87.634621000002
                			},
                			{
                				"stpid": "14596",
                				"stpnm": "Division & Western",
                				"lat": 41.903007999999,
                				"lon": -87.686722000001
                			},
                			{
                				"stpid": "17131",
                				"stpnm": "Division & Wood",
                				"lat": 41.903199999999,
                				"lon": -87.672129999999
                			},
                			{
                				"stpid": "18412",
                				"stpnm": "Walton & Dearborn",
                				"lat": 41.899893,
                				"lon": -87.630967
                			}
                		]
                	}
                }"""
  val predctions = """{
                     	"bustime-response": {
                     		"prd": [
                     			{
                     				"tmstmp": "20190918 12:46",
                     				"typ": "A",
                     				"stpnm": "Division & Richmond",
                     				"stpid": "2000",
                     				"vid": "1995",
                     				"dstp": 2323,
                     				"rt": "70",
                     				"rtdd": "70",
                     				"rtdir": "Eastbound",
                     				"des": "Walton & Dearborn",
                     				"prdtm": "20190918 12:49",
                     				"tablockid": "70 -808",
                     				"tatripid": "88347488",
                     				"dly": false,
                     				"prdctdn": "2",
                     				"zone": ""
                     			},
                     			{
                     				"tmstmp": "20190918 12:46",
                     				"typ": "A",
                     				"stpnm": "Division & Richmond",
                     				"stpid": "2000",
                     				"vid": "1989",
                     				"dstp": 16259,
                     				"rt": "70",
                     				"rtdd": "70",
                     				"rtdir": "Eastbound",
                     				"des": "Walton & Dearborn",
                     				"prdtm": "20190918 13:06",
                     				"tablockid": "70 -811",
                     				"tatripid": "88347489",
                     				"dly": false,
                     				"prdctdn": "19",
                     				"zone": ""
                     			}
                     		]
                     	}
                     }"""
}