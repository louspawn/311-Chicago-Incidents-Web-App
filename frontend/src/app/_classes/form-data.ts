
export class FormData {
  public static readonly reportTypes = [
      { name: 'Abandoned Vehicle', value: 'Abandoned Vehicle' },
      { name: 'Alley Light Out', value: 'Alley Light Out' },
      { name: 'Garbage Cart', value: 'Garbage Cart' },
      { name: 'Graffiti Removal', value: 'Graffiti Removal' },
      { name: 'Pot Hole', value: 'Pot Hole' },
      { name: 'Rodent Baiting', value: 'Rodent Baiting' },
      { name: 'Sanitation Code Complaint', value: 'Sanitation Code Complaint' },
      { name: 'Lights All Out', value: 'Lights All Out' },
      { name: 'Street Light One Out', value: 'Street Light One Out' },
      { name: 'Tree Debris', value: 'Tree Debris' },
      { name: 'Tree Trims', value: 'Tree Trims' }
  ];

  public static readonly vehicleModel = [
    {
      'name': '(Assembled From Parts,Homemade)',
      'value': 'ASVEAV'
    },
    {
      'name': '(Homemade Motorcycle, Moped.Etc.)',
      'value': 'HOMDCYL'
    },
    {
      'name': '(Homemade Trailer)',
      'value': 'HMDETL'
    },
    {
      'name': '(Reconstructed Motor Home)',
      'value': 'REMHTK'
    },
    {
      'name': '(Reconstructed Trailers)',
      'value': 'RCONTL'
    },
    {
      'name': '(See Lancia Make)',
      'value': 'LNCI'
    },
    {
      'name': '(Unlisted Construction Equipment Make)',
      'value': 'COEQCE'
    },
    {
      'name': '(Unlisted Farm/Garden Equipment Make)',
      'value': 'FARMFE'
    },
    {
      'name': '(Unlisted Make)',
      'value': 'CYCLCYL'
    },
    {
      'name': '(Unlisted Manufacturer)',
      'value': 'TRLRTL'
    },
    {
      'name': 'Acura',
      'value': 'ACUR'
    },
    {
      'name': 'Aero Glass Boat Co.',
      'value': 'AERGTL'
    },
    {
      'name': 'Alfa Romeo',
      'value': 'ALFA'
    },
    {
      'name': 'All Terrain - No Vmo',
      'value': 'ATV'
    },
    {
      'name': 'American Motors',
      'value': 'AMER'
    },
    {
      'name': 'Audi',
      'value': 'AUDI'
    },
    {
      'name': 'Austin',
      'value': 'AUST'
    },
    {
      'name': 'Bentley',
      'value': 'BENT'
    },
    {
      'name': 'Bmw',
      'value': 'BMW'
    },
    {
      'name': 'Buick',
      'value': 'BUIC'
    },
    {
      'name': 'Cadillac',
      'value': 'CADI'
    },
    {
      'name': 'Campers, Inc.',
      'value': 'CAMRTL'
    },
    {
      'name': 'Caterpillar Tractor Co.',
      'value': 'CATCE'
    },
    {
      'name': 'Chappy (Mfd. By Yamaha Motor Corp.)',
      'value': 'YAMACYL'
    },
    {
      'name': 'Chevrolet',
      'value': 'CHEV'
    },
    {
      'name': 'Chrysler',
      'value': 'CHRY'
    },
    {
      'name': 'Cozy Craft (Mfd. By Travelcraft, Inc.)',
      'value': 'TRVATK'
    },
    {
      'name': 'Craftsmam',
      'value': 'CRFTFE'
    },
    {
      'name': 'Daewoo',
      'value': 'DAEWOO'
    },
    {
      'name': 'Daimler',
      'value': 'DAIM'
    },
    {
      'name': 'Datsun',
      'value': 'DATS'
    },
    {
      'name': 'Deere, John; Deere &amp; Co.',
      'value': 'DEERCE'
    },
    {
      'name': 'Dodge',
      'value': 'DODG'
    },
    {
      'name': 'Doolittle',
      'value': 'DOOLTL'
    },
    {
      'name': 'Dune Buggy Open Body',
      'value': 'DUNOP'
    },
    {
      'name': 'Engine',
      'value': 'ENGNENG'
    },
    {
      'name': 'Factory Outlet Trailers',
      'value': 'FACTTL'
    },
    {
      'name': 'Fiat',
      'value': 'FIAT'
    },
    {
      'name': 'Ford',
      'value': 'FORD'
    },
    {
      'name': 'Freightliner Corp.',
      'value': 'FRHTTK'
    },
    {
      'name': 'Fruehauf Corp',
      'value': 'FRUETL'
    },
    {
      'name': 'General Motors Corp.',
      'value': 'GMCCE'
    },
    {
      'name': 'Geo/Metro',
      'value': 'GEOMET'
    },
    {
      'name': 'Geo/Prism',
      'value': 'GEOPRI'
    },
    {
      'name': 'Geo/Storm',
      'value': 'GEOSTO'
    },
    {
      'name': 'Geo/Truck(Tracker)',
      'value': 'GEOTK'
    },
    {
      'name': 'Go Kart',
      'value': 'GOKTCYL'
    },
    {
      'name': 'Golf Cart Open Body',
      'value': 'GOFOP'
    },
    {
      'name': 'Golf Or Go Cart Or Dune Buggy',
      'value': 'SPEC'
    },
    {
      'name': 'Great Dane Trailers Inc.',
      'value': 'GDANTL'
    },
    {
      'name': 'Halliburton Co.',
      'value': 'HALRTL'
    },
    {
      'name': 'Harley-Davidson',
      'value': 'HDCYL'
    },
    {
      'name': 'Honda',
      'value': 'HOND'
    },
    {
      'name': 'Humbee Surrey',
      'value': 'HUME'
    },
    {
      'name': 'Husqvarna',
      'value': 'HUSQCYL'
    },
    {
      'name': 'Hyundai',
      'value': 'HYUN'
    },
    {
      'name': 'Infiniti/M30',
      'value': 'INFIM30'
    },
    {
      'name': 'Infiniti/Q45',
      'value': 'INFIQ45'
    },
    {
      'name': 'International Harvester',
      'value': 'INTLTL'
    },
    {
      'name': 'Isuzu',
      'value': 'ISU'
    },
    {
      'name': 'Jaguar',
      'value': 'JAGU'
    },
    {
      'name': 'Jayco, Inc.',
      'value': 'JAYTL'
    },
    {
      'name': 'Jeep (If Mfd. Prior To 1970)',
      'value': 'JEPTK'
    },
    {
      'name': 'Jeep - Year Greater 1988',
      'value': 'JEEPTK'
    },
    {
      'name': 'Jeep/Cherokee',
      'value': 'JEEPCHE'
    },
    {
      'name': 'Karmann-Ghia (Vma Was Karg)',
      'value': 'VOLKKAR'
    },
    {
      'name': 'Kawasaki Motors Corp.',
      'value': 'KAWK'
    },
    {
      'name': 'Kenworth Motor Truck Co.',
      'value': 'KWTK'
    },
    {
      'name': 'Kia Motors Corp',
      'value': 'KIATK'
    },
    {
      'name': 'Lamborghini/Countach',
      'value': 'LAMOCOU'
    },
    {
      'name': 'Lamborghini/Espada',
      'value': 'LAMOESP'
    },
    {
      'name': 'Lamborghini/Jalpa',
      'value': 'LAMOJAL'
    },
    {
      'name': 'Lamborghini/Jarma',
      'value': 'LAMOJAR'
    },
    {
      'name': 'Lamborghini/Miura Sv',
      'value': 'LAMOMIU'
    },
    {
      'name': 'Land Rover',
      'value': 'LNDRLR'
    },
    {
      'name': 'Legacy Unknown',
      'value': 'LGMKUNK'
    },
    {
      'name': 'Lexus',
      'value': 'LEXS'
    },
    {
      'name': 'Lincoln',
      'value': 'LINC'
    },
    {
      'name': 'Low Boy Trailer',
      'value': 'LOWBTL'
    },
    {
      'name': 'Mack Trucks, Inc.',
      'value': 'MACKTK'
    },
    {
      'name': 'Maserati',
      'value': 'MASE'
    },
    {
      'name': 'Mazda',
      'value': 'MAZD'
    },
    {
      'name': 'Mercedes',
      'value': 'MERZ'
    },
    {
      'name': 'Mercury',
      'value': 'MERC'
    },
    {
      'name': 'Merkur',
      'value': 'MERK'
    },
    {
      'name': 'Mg/Midget',
      'value': 'MGMID'
    },
    {
      'name': 'Mitsubishi',
      'value': 'MITS'
    },
    {
      'name': 'Mobile Home Co.',
      'value': 'MOBLTL'
    },
    {
      'name': 'Monon Trailer, Div. Of Evans Products Co.',
      'value': 'MONNTL'
    },
    {
      'name': 'Nash',
      'value': 'NASHTK'
    },
    {
      'name': 'Nimrod Travel Trailer',
      'value': 'NIMRTL'
    },
    {
      'name': 'Nissan',
      'value': 'NISS'
    },
    {
      'name': 'Oldsmobile',
      'value': 'OLDS'
    },
    {
      'name': 'Opel',
      'value': 'OPEL'
    },
    {
      'name': 'Packard',
      'value': 'PACK'
    },
    {
      'name': 'Peterbilt Motors Co., Division Paccar, Inc.',
      'value': 'PTRBTK'
    },
    {
      'name': 'Peugeot',
      'value': 'PEUGCYL'
    },
    {
      'name': 'Plymouth',
      'value': 'PLYM'
    },
    {
      'name': 'Pontiac',
      'value': 'PONT'
    },
    {
      'name': 'Porsche',
      'value': 'PORS'
    },
    {
      'name': 'Rambler',
      'value': 'RAMB'
    },
    {
      'name': 'Range Rover of North America',
      'value': 'RROVTK'
    },
    {
      'name': 'Reconstructed',
      'value': 'RECOCYL'
    },
    {
      'name': 'Renault',
      'value': 'RENA'
    },
    {
      'name': 'Renault/Alliance',
      'value': 'AMERALI'
    },
    {
      'name': 'Rolls Royce',
      'value': 'ROL'
    },
    {
      'name': 'Saab',
      'value': 'SAA'
    },
    {
      'name': 'Saturn',
      'value': 'SATR'
    },
    {
      'name': 'Seagull',
      'value': 'SEAGCYL'
    },
    {
      'name': 'Snowmobile - No Vmo',
      'value': 'SNOW'
    },
    {
      'name': 'Starcraft Corp.',
      'value': 'STAOTK'
    },
    {
      'name': 'Stoughton Trailers, Inc.',
      'value': 'STOUTL'
    },
    {
      'name': 'Studebaker',
      'value': 'STU_'
    },
    {
      'name': 'Subaru',
      'value': 'SUBA'
    },
    {
      'name': 'Suzuki',
      'value': 'SUZI'
    },
    {
      'name': 'Tennesse Trailer',
      'value': 'TENNTL'
    },
    {
      'name': 'Toyota',
      'value': 'TOYT'
    },
    {
      'name': 'Trailco Mfg. Sales Co., Div. Of Dorsey Trailers',
      'value': 'TRLCTL'
    },
    {
      'name': 'Transcraft Corp.',
      'value': 'TRAOTL'
    },
    {
      'name': 'Transmission - No Vmo',
      'value': 'TRMN'
    },
    {
      'name': 'Triumph',
      'value': 'TRIU'
    },
    {
      'name': 'U-Haul Co, Division Parkhurst Manufacturing Co.',
      'value': 'UHAUTL'
    },
    {
      'name': 'Vehicle Part',
      'value': 'PART'
    },
    {
      'name': 'Vespa',
      'value': 'VESP'
    },
    {
      'name': 'Volkswagen',
      'value': 'VOLK'
    },
    {
      'name': 'Volvo',
      'value': 'VOLV'
    },
    {
      'name': 'Wasp',
      'value': 'WASPCYL'
    },
    {
      'name': 'Winnebago Industries, Inc.',
      'value': 'WINNTL'
    },
    {
      'name': 'Zcz (Yugoslavia)',
      'value': 'ZCZY'
    }
  ];

  public static readonly vehicleColor = [
    {
      'name': 'Beige',
      'value': 'BGE'
    },
    {
      'name': 'Black',
      'value': 'BLK'
    },
    {
      'name': 'Blue',
      'value': 'BLU'
    },
    {
      'name': 'Bronze',
      'value': 'BRZ'
    },
    {
      'name': 'Brown',
      'value': 'BRO'
    },
    {
      'name': 'Burgundy',
      'value': 'BUR'
    },
    {
      'name': 'Chrome',
      'value': 'COM'
    },
    {
      'name': 'Copper',
      'value': 'CPR'
    },
    {
      'name': 'Cream',
      'value': 'CRM'
    },
    {
      'name': 'Gold',
      'value': 'GLD'
    },
    {
      'name': 'Gray',
      'value': 'GRY'
    },
    {
      'name': 'Green',
      'value': 'GRN'
    },
    {
      'name': 'Maroon',
      'value': 'MAR'
    },
    {
      'name': 'Multi-Color',
      'value': 'MUL'
    },
    {
      'name': 'Orange',
      'value': 'ONG'
    },
    {
      'name': 'Pink',
      'value': 'PNK'
    },
    {
      'name': 'Purple',
      'value': 'PLE'
    },
    {
      'name': 'Red',
      'value': 'RED'
    },
    {
      'name': 'Silver',
      'value': 'SIL'
    },
    {
      'name': 'Star',
      'value': 'STAR'
    },
    {
      'name': 'Stripes',
      'value': 'STRIPE'
    },
    {
      'name': 'Tan',
      'value': 'TAN'
    },
    {
      'name': 'Turquoise',
      'value': 'TRQ'
    },
    {
      'name': 'Unknown',
      'value': 'UNKNOWN'
    },
    {
      'name': 'White',
      'value': 'WHI'
    },
    {
      'name': 'Yellow',
      'value': 'YEL'
    }
  ];

  public static readonly graffitiLocation = [
    {
      'name': 'Alley',
      'value': 'ALLEY'
    },
    {
      'name': 'Bench',
      'value': 'BENCH'
    },
    {
      'name': 'Door',
      'value': 'DOOR'
    },
    {
      'name': 'Dumpster',
      'value': 'DUMPSTER'
    },
    {
      'name': 'Express Way Job',
      'value': 'EXPRESS'
    },
    {
      'name': 'Fence',
      'value': 'FENCE'
    },
    {
      'name': 'Front',
      'value': 'FRONT'
    },
    {
      'name': 'Garage',
      'value': 'GARAGE'
    },
    {
      'name': 'Garbage Cart',
      'value': 'GARBAGE'
    },
    {
      'name': 'Hydrant',
      'value': 'HYDRANT'
    },
    {
      'name': 'Mail Box',
      'value': 'MAILBOX'
    },
    {
      'name': 'Newspaper Box',
      'value': 'NEWSBOX'
    },
    {
      'name': 'Phone',
      'value': 'PHONE'
    },
    {
      'name': 'Pole',
      'value': 'POLE'
    },
    {
      'name': 'Rear',
      'value': 'REAR'
    },
    {
      'name': 'Side',
      'value': 'SIDE'
    },
    {
      'name': 'Sign',
      'value': 'SIGN'
    },
    {
      'name': 'Traffic Control Box',
      'value': 'TRAFFIC'
    }
  ];

  public static readonly graffitiSurface = [
    {
      'name': 'Aluminum Siding',
      'value': 'ALUM'
    },
    {
      'name': 'Asphalt',
      'value': 'ASPHALT'
    },
    {
      'name': 'Brick - Painted',
      'value': 'BP'
    },
    {
      'name': 'Brick - Unpainted',
      'value': 'BU'
    },
    {
      'name': 'Cement (Sidewalk, Alley, Wall, Curb)',
      'value': 'CEMENT2'
    },
    {
      'name': 'Glass',
      'value': 'GLASS'
    },
    {
      'name': 'Limestone',
      'value': 'LIMESTON'
    },
    {
      'name': 'Marble/Granite',
      'value': 'MARBLEG'
    },
    {
      'name': 'Metal - Painted',
      'value': 'METAL'
    },
    {
      'name': 'Metal - Unpainted',
      'value': 'MTLUPNTD'
    },
    {
      'name': 'Other/Unknown Surface',
      'value': 'UNK'
    },
    {
      'name': 'Stucco',
      'value': 'STUCCO'
    },
    {
      'name': 'Tree',
      'value': 'TREE'
    },
    {
      'name': 'Vinyl Siding',
      'value': 'VINYL'
    },
    {
      'name': 'Wood - Painted',
      'value': 'WP'
    },
    {
      'name': 'Wood - Unpainted',
      'value': 'WU'
    }
  ];

  public static readonly sanitationCodeNature = [
    {
      'name': 'Construction Site Cleanliness/Fence',
      'value': 'CONS'
    },
    {
      'name': 'Dog feces in yard',
      'value': 'DOGYARD'
    },
    {
      'name': 'Dumpster not being emptied',
      'value': 'DUMPSTER'
    },
    {
      'name': 'Garbage in alley',
      'value': 'GARALLEY'
    },
    {
      'name': 'Garbage in yard',
      'value': 'GARYARD'
    },
    {
      'name': 'Graffiti Commercial Vehicle',
      'value': 'GRAFFITI'
    },
    {
      'name': 'Other',
      'value': 'OTHER'
    },
    {
      'name': 'Overflowing carts',
      'value': 'OVERFLOW'
    },
    {
      'name': 'Standing water',
      'value': 'STANDING'
    }
  ];

  public static readonly treeLocation = [
    {
      'name': 'Alley',
      'value': 'ALLEY'
    },
    {
      'name': 'Parkway',
      'value': 'PARKWAY'
    },
    {
      'name': 'Vacant Lot',
      'value': 'VACNTLOT'
    }
  ];

  static readonly options = ``;

  static createOptionsJSON(optionsString: String) {
    const optionList = [];
    const optionsArray = optionsString.split('\n');

    optionsArray.forEach(line => {
      const valueStart = line.indexOf('\"') + 1;
      const valueEnd = line.indexOf('\"', valueStart);
      const value = line.substring(valueStart, valueEnd);

      const nameStart = line.indexOf('>') + 1;
      const nameEnd = line.indexOf('</', nameStart);
      const name = line.substring(nameStart, nameEnd);

      optionList.push({ name: name, value: value });
    });

    return optionList;
  }
}
