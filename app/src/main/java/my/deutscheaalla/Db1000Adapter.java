package my.deutscheaalla;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


public class Db1000Adapter {
    myDbHelper1000 myhelper;

    private ArrayList<Db1000Adapter.CurrentNode> Currentnodes;

    public Db1000Adapter(Context context)
    {
        myhelper = new myDbHelper1000(context);
    }

    static class myDbHelper1000 extends SQLiteOpenHelper
    {
        private static final String DATABASE_NAME = "myDatabase";    // Database Name
        private static final String TABLE_NAME = "words1000";   // Table Name
        private static final int DATABASE_Version = 1;   // Database Version
        private static final String UID="id";     // Column I (Primary Key)
        private static final String DE = "DE";    //Column II
        private static final String RU = "RU";    // Column III
        private static final String UA = "UA";    // Column UA
        private static final String Sound= "Sound";    // Column III
        private static final String Lessons= "lesson";    // Column 4
        private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+
                " ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+ DE +" VARCHAR(255) ,"+ RU +" VARCHAR(225), "+ UA +" VARCHAR(225), "+ Sound +" VARCHAR(225), "+ Lessons +" INTEGER);";
        //private static final String DROP_TABLE ="DROP TABLE IF EXISTS "+TABLE_NAME;
        private Context context;

        public myDbHelper1000(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_Version);
            this.context=context;
        }

        public void onCreate(SQLiteDatabase db) {

            try {
                db.execSQL(CREATE_TABLE);
            } catch (Exception e) {
                Message.message(context,""+e);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                Message.message(context,"OnUpgrade");
                //db.execSQL(DROP_TABLE);
                //onCreate(db);
            }catch (Exception e) {
                Message.message(context,""+e);
            }
        }
    }

    public long addLessonData(String de, String ru, String ua, String sound, int lesson)
    {
        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Db1000Adapter.myDbHelper1000.DE, de);
        contentValues.put(Db1000Adapter.myDbHelper1000.RU, ru);
        contentValues.put(Db1000Adapter.myDbHelper1000.UA, ua);
        contentValues.put(Db1000Adapter.myDbHelper1000.Sound, sound);
        contentValues.put(Db1000Adapter.myDbHelper1000.Lessons, lesson);
        long id = dbb.insert(Db1000Adapter.myDbHelper1000.TABLE_NAME, null , contentValues);
        return id;
    }

    public ArrayList getDataArray() {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {Db1000Adapter.myDbHelper1000.UID, Db1000Adapter.myDbHelper1000.DE, Db1000Adapter.myDbHelper1000.RU, Db1000Adapter.myDbHelper1000.UA, Db1000Adapter.myDbHelper1000.Sound, Db1000Adapter.myDbHelper1000.Lessons};
        Cursor cursor = db.query(Db1000Adapter.myDbHelper1000.TABLE_NAME, columns, null, null, null, null, null);
        StringBuffer buffer = new StringBuffer();

        String[][] allData;
        //ArrayList<CurrentNode> Currentnodes = null;
        Currentnodes = new ArrayList<Db1000Adapter.CurrentNode>();
        while (cursor.moveToNext()) {
            int uid = cursor.getInt(cursor.getColumnIndex(Db1000Adapter.myDbHelper1000.UID));
            String DE = cursor.getString(cursor.getColumnIndex(Db1000Adapter.myDbHelper1000.DE));
            String RU = cursor.getString(cursor.getColumnIndex(Db1000Adapter.myDbHelper1000.RU));
            String UA = cursor.getString(cursor.getColumnIndex(Db1000Adapter.myDbHelper1000.UA));
            String Sound = cursor.getString(cursor.getColumnIndex(Db1000Adapter.myDbHelper1000.Sound));
            int Lesson = cursor.getInt(cursor.getColumnIndex(Db1000Adapter.myDbHelper1000.Lessons));
            String[] onedate = {uid + "", DE, RU, Lesson + "", Sound, UA};

            //Currentnodes = new ArrayList<CurrentNode>();
            Currentnodes.add(new Db1000Adapter.CurrentNode(onedate));
            //buffer.append(cid+ "   " + name + "   " + password +" " + lesson +" \n");
            //buffer.append("hi \n");

        }
        return Currentnodes;
    }

    public String[][] getDataArrayLesson(String Lesson) {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {Db1000Adapter.myDbHelper1000.UID, Db1000Adapter.myDbHelper1000.DE, Db1000Adapter.myDbHelper1000.RU, Db1000Adapter.myDbHelper1000.UA, Db1000Adapter.myDbHelper1000.Sound, Db1000Adapter.myDbHelper1000.Lessons};
        // Filter results WHERE "title" = 'My Title'
        String selection = Db1000Adapter.myDbHelper1000.Lessons + " = ?";
        String[] selectionArgs = { Lesson };
        Cursor cursor =  db.query(Db1000Adapter.myDbHelper1000.TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        StringBuffer buffer = new StringBuffer();

        String[][] allData = new String[25][];
        int i = 0;
        while (cursor.moveToNext()) {
            int uid = cursor.getInt(cursor.getColumnIndex(Db1000Adapter.myDbHelper1000.UID));
            String DE = cursor.getString(cursor.getColumnIndex(Db1000Adapter.myDbHelper1000.DE));
            String RU = cursor.getString(cursor.getColumnIndex(Db1000Adapter.myDbHelper1000.RU));
            String UA = cursor.getString(cursor.getColumnIndex(Db1000Adapter.myDbHelper1000.UA));
            String Sound = cursor.getString(cursor.getColumnIndex(Db1000Adapter.myDbHelper1000.Sound));
            int Lessons = cursor.getInt(cursor.getColumnIndex(Db1000Adapter.myDbHelper1000.Lessons));
            String[] onedate = {uid + "", DE, RU, Lessons + "", Sound, UA};
            allData[i] = onedate;
            //Currentnodes = new ArrayList<CurrentNode>();
            //Currentnodes.add(new CurrentNode(onedate));
            //buffer.append(cid+ "   " + name + "   " + password +" " + lesson +" \n");
            i++;
        }
        return allData;
        //return buffer;
    }

    public String[][] getFullDataArray() {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {Db1000Adapter.myDbHelper1000.UID, Db1000Adapter.myDbHelper1000.DE, Db1000Adapter.myDbHelper1000.RU, Db1000Adapter.myDbHelper1000.UA, Db1000Adapter.myDbHelper1000.Sound, Db1000Adapter.myDbHelper1000.Lessons};
        // Filter results WHERE "title" = 'My Title'
        //String selection = Db1000Adapter.myDbHelper1000.Lessons + " = ?";
        Cursor cursor =  db.query(Db1000Adapter.myDbHelper1000.TABLE_NAME, columns, null,null, null, null, null);
        StringBuffer buffer = new StringBuffer();

        String[][] allData = new String[1092][];
        int i = 0;
        while (cursor.moveToNext()) {
            int uid = cursor.getInt(cursor.getColumnIndex(Db1000Adapter.myDbHelper1000.UID));
            String DE = cursor.getString(cursor.getColumnIndex(Db1000Adapter.myDbHelper1000.DE));
            String RU = cursor.getString(cursor.getColumnIndex(Db1000Adapter.myDbHelper1000.RU));
            String UA = cursor.getString(cursor.getColumnIndex(Db1000Adapter.myDbHelper1000.UA));
            String Sound = cursor.getString(cursor.getColumnIndex(Db1000Adapter.myDbHelper1000.Sound));
            int Lessons = cursor.getInt(cursor.getColumnIndex(Db1000Adapter.myDbHelper1000.Lessons));
            String[] onedate = {uid + "", DE, RU, Lessons + "", Sound, UA};
            allData[i] = onedate;
            //Currentnodes = new ArrayList<CurrentNode>();
            //Currentnodes.add(new CurrentNode(onedate));
            //buffer.append(cid+ "   " + name + "   " + password +" " + lesson +" \n");
            i++;
        }
        return allData;
        //return buffer;
    }

    public void emptyTable(String tableName)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS "+tableName);
    }

    public void createTable()
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        myhelper.onCreate(db);

    }

    private class CurrentNode {

        private String [] nodeData;

        public CurrentNode(String [] nodeData) {
            this.nodeData = nodeData;
        }

        public String getNodeData(int nodeIndex) {
            return nodeData[nodeIndex];
        }

        @Override
        public String toString() {
            String st = "";
            for (int i = 0; i < 6; i++) {
                st += nodeData[i]+" ";
            }
            return st;
        }

    }

    public int checkTable(){
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String Query = "SELECT name FROM sqlite_master WHERE type='table' AND name='"+ Db1000Adapter.myDbHelper1000.TABLE_NAME+"'";
        int name = 0;
        try
        {
            Cursor c = null;
            //c = db.rawQuery("select name from person where id="+id, null);
            c = db.rawQuery(Query, null);
            c.moveToFirst();
            name = c.getCount();
            ///System.out.println(name);
            c.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        //if(name == 0) {
        //    createTable();
        //}

        //String [][] allData = getDataArrayLesson("1");
        return name;
        //return allData.length;
    }

    public void addData() {
        String [][] allCurrentLesson1 = {
                {"Hallo!","Привет!","a1","Привіт"},
                {"Guten Tag!","Добрый день!","a2", "Добрий день!"},
                {"Entschuldigung!","Извини(те)!","a3","Вибач(те)!"},
                {"Bitte","пожалуйста","a4","Будь-ласка"},
                {"Danke","спасибо","a5","Дякую"},
                {"Gern geschehen","не за что","a6","Нема за що"},
                {"Schade","жаль","a7","Шкода"},
                {"Tschüss!","Пока!","a8","Бувай(пока)"},
                {"Auf Wiedersehen!","До свидания!","a9","До побачення"},
                {"die Leute","люди","a10","Люди"},
                {"der Mensch","человек","a11","Людина"},
                {"der Mann","мужчина; муж","a12","Чоловiк"},
                {"die Frau","женщина; жена","a13","Жiнка; дружина"},
                {"das Kind","ребенок","a14","Дитина"},
                {"der Junge","мальчик","a15","Хлопчик"},
                {"das Mädchen","девочка","a16","Дiвчинка"},
                {"der Freund","друг","a17","Друг"},
                {"der Bekannte","знакомый","a18","Знайомий"},
                {"der Nachbar","сосед","a19","Сусiд"},
                {"der Gast","гость","a20","Гiсть"},
                {"der Chef","начальник; шеф","a21","Керiвник; шеф"},
                {"der Konkurrent","конкурент","a22","Конкурент"},
                {"der Kunde","клиент, покупатель","a23","Клієнт, покупець"},
                {"der Kollege","коллега","a24","Колега"},
                {"die Familie","семья","a25","Сім'я"},
        };

        for (int i = 0; i < allCurrentLesson1.length; i++) {
            addLessonData(allCurrentLesson1[i][0],allCurrentLesson1[i][1],allCurrentLesson1[i][3],allCurrentLesson1[i][2],1);
        }


        String [][] allCurrentLesson2 = {
                {"die Eltern","родители","a26","Батьки"},
                {"der Vater","отец","a27","Батько"},
                {"der Vati","папа","a28","Тато"},
                {"die Mutter","мать","a29","Мати"},
                {"die Mutti","мама","a30","Мама"},
                {"der Sohn","сын","a31","Син"},
                {"die Tochter","дочь","a32","Донька"},
                {"der Bruder","брат","a33","Брат"},
                {"die Schwester","сестра","a34","Сестра"},
                {"der Großvater","дед","a35","Дiд"},
                {"der Opa","дедушка","a36","Дiдусь"},
                {"die Großmutter","бабушка","a37","Бабушка"},
                {"die Oma","бабуля","a38","Бабуля"},
                {"der Enkel","внук","a39","Онук"},
                {"die Enkelin","внучка","a40","Онучка"},
                {"der Schwiegervater","тесть, свекор","a41","Тесть, свекор"},
                {"die Schwiegermutter","тёща, свекровь","a42","Теща, свекруха"},
                {"der Onkel","дядя","a43","Дядько",},
                {"die Tante","тетя","a44","Тiтка"},
                {"der Cousin [кузэн]","двоюродный брат","a45","Двоюрiдний брат"},
                {"die Kusine","двоюродная сестра","a46","Двоюрiдна сестра"},
                {"der Neffe","племянник","a47","Племiнник"},
                {"die Nichte","племянница","a48","Племiнниця"},
                {"die Arbeit","работа","a49","Робота"},
                {"der Geschäftsmann","бизнесмен","a50","Бiзнесмен"},
        };

        for (int i = 0; i < allCurrentLesson2.length; i++) {
            addLessonData(allCurrentLesson2[i][0],allCurrentLesson2[i][1],allCurrentLesson2[i][3],allCurrentLesson2[i][2],2);
        }


        String [][] allCurrentLesson3 = {
                {"der Lehrer","учитель","a51","Вчитель"},
                {"der Fahrer","водитель","a52","Водiй"},
                {"der Arbeiter","рабочий","a53","Робiтник"},
                {"der Ingenieur","инженер","a54","Iнженер"},
                {"der Arzt","врач","a55","Лiкар"},
                {"der Rechtsanwalt","адвокат","a56","Адвокат"},
                {"der Journalist","журналист","a57","Журналiст"},
                {"der Krankenschwester","медсестра","a58","Медсестра"},
                {"der Verkäufer","продавец","a59","Продавець"},
                {"der Kellner","официант","a60","Офіціант"},
                {"der Buchhalter","бухгалтер","a61","Бухгалтер"},
                {"der Maler","художник","a62","Художник"},
                {"der Musiker","музыкант","a63","Музикант"},
                {"der Schauspieler","актер","a64","Актор"},
                {"der Student","студент","a65","Студент"},
                {"der Schüler","школьник, ученик","a66","школяр, учень"},
                {"das Tier","животное","a67","тварина"},
                {"die Katze","кошка","a68","кішка"},
                {"der Hund","собака","a69","пес"},
                {"der Vogel","птица","a70","птах"},
                {"das Eichhörnchen","белка","a71","бiлка"},
                {"der Wolf","волк","a72","вовк"},
                {"die Gans","гусь","a73","гусь"},
                {"die Giraffe","жираф","a74","жираф"},
                {"das Kaninchen","кролик","a75","кролик"},
        };

        for (int i = 0; i < allCurrentLesson3.length; i++) {
            addLessonData(allCurrentLesson3[i][0],allCurrentLesson3[i][1],allCurrentLesson3[i][3],allCurrentLesson3[i][2],3);
        }


        String [][] allCurrentLesson4 = {
                {"der Hase","заяц","a76","заяць"},
                {"die Kuh","корова","a77","корова"},
                {"die Ratte","крыса","a78","криса"},
                {"der Fuchs","лиса","a79","лисиця"},
                {"das Pferd","лошадь","a80","Кінь"},
                {"der Frosch","лягушка","a81","Жаба"},
                {"der Bär","медведь","a82","Ведмідь"},
                {"die Maus","мышь","a83","Миша"},
                {"der Affe","обезьяна","a84","Мавпа"},
                {"das Schwein","свинья","a85","Свиня"},
                {"der Elefant","слон","a86","Слон"},
                {"die Ente","утка","a87","Качка"},
                {"das Land","страна; сельская местность; земля","a88","країна;сільська місцевість;земля"},
                {"Russland","Россия","a89","Россiя"},
                {"Deutschland","Германия","a90","Германiя"},
                {"Österreich","Австрия","a91","Австрiя"},
                {"die Schweiz","Швейцария","a92","Швейцарiя"},
                {"die Stadt","город","a93","мiсто"},
                {"das Haus","дом","a94","Будинок"},
                {"das Gebäude","здание","a95", "Будівля"},
                {"der Platz","площадь; место","a96", "Площа; місце"},
                {"der Eingang","вход","a97","Вхід"},
                {"der Ausgang","выход","a98","Вихід"},
                {"das Zentrum","центр","a99","Центр"},
                {"der Hof","двор","a100","Двір"},
        };

        for (int i = 0; i < allCurrentLesson4.length; i++) {
            addLessonData(allCurrentLesson4[i][0],allCurrentLesson4[i][1],allCurrentLesson4[i][3],allCurrentLesson4[i][2],4);
        }


        String [][] allCurrentLesson5 = {
                {"das Dach","крыша","a101","Дах"},
                {"der Zaun","забор","a102", "Паркан"},
                {"das Dorf","деревня, поселок","a103","Село,селище"},
                {"die Schule","школа","a104","Школа"},
                {"die Universität","университет","a105","Університет"},
                {"das Theater","театр","a106","Театр"},
                {"die Kirche","церковь","a107","Церква"},
                {"das Restaurant","ресторан","a108","Ресторан"},
                {"das Cafe","кафе","a109","Кафе"},
                {"das Hotel","гостиница","a110","Готель"},
                {"die Bank","банк","a111","Банк"},
                {"das Kino","кинотеатр","a112","Кiнотеатр"},
                {"das Krankenhaus","больница","a113", "Лікарня"},
                {"die Polizei","полиция","a114", "Поліція"},
                {"das Postamt","почта","a115","Пошта"},
                {"der Bahnhof","станция, вокзал","a116","станція, вокзал"},
                {"der Flughafen","аэропорт","a117","Аеропорт"},
                {"das Geschäft","магазин","a118","Магазин"},
                {"die Apotheke","аптека","a119","Аптека"},
                {"der Markt","рынок","a120","Базар"},
                {"das Büro","офис","a121", "Офіс"},
                {"die Firma","фирма","a122", "Фірма"},
                {"der Betrieb","предприятие","a123", "Підприємство"},
                {"die Straße","улица","a124","Вулиця"},
                {"der Weg","дорога","a125","Дорога"},
        };

        for (int i = 0; i < allCurrentLesson5.length; i++) {
            addLessonData(allCurrentLesson5[i][0],allCurrentLesson5[i][1],allCurrentLesson5[i][3],allCurrentLesson5[i][2],5);
        }


        String [][] allCurrentLesson6 = {
                {"die Kreuzung","перекрёсток","a126","Перехрестя"},
                {"die Haltestelle","остановка","a127","Зупинка"},
                {"der Gehsteig","тротуар","a128","Тротуар"},
                {"der Pfad","тропа, тропинка","a129","Тропа"},
                {"der Garten","сад","a130","Сад"},
                {"der Park","парк","a131","Парк"},
                {"die Brücke","мост","a132", "Міст"},
                {"der Fluss","река","a133","Річка"},
                {"der Wald","лес","a134","Ліс"},
                {"das Feld","поле","a135","Поле"},
                {"der Berg","гора","a136","Гора"},
                {"der See","озеро","a137","Озеро"},
                {"das Meer","море","a138","Море"},
                {"der Ozean","океан","a139","Океан"},
                {"die Küste","морской берег, побережье","a140","Морський берег,узбережжя"},
                {"der Strand","пляж","a141","Пляж"},
                {"der Sand","песок","a142","Пiсок"},
                {"die Insel","остров","a143","Острів"},
                {"die Grenze","граница","a144","Кордон"},
                {"der Zoll","таможня","a145","Митниця"},
                {"der Müll","мусор","a146", "Сміття"},
                {"die Abfälle","отходы","a147", "Відходи"},
                {"der Stein","камень","a148","Камінь"},
                {"die Pflanze","растение","a149", "Рослина"},
                {"der Baum","дерево","a150","Дерево"},
        };

        for (int i = 0; i < allCurrentLesson6.length; i++) {
            addLessonData(allCurrentLesson6[i][0],allCurrentLesson6[i][1],allCurrentLesson6[i][3],allCurrentLesson6[i][2],6);
        }


        String [][] allCurrentLesson7 = {
                {"das Gras","трава","a151","Трава"},
                {"die Blume","цветок","a152", "Квітка"},
                {"das Blatt","лист","a153","Лист"},
                {"die Wohnung","квартира","a154","Квартира"},
                {"das Zimmer","комната","a155","Кімната"},
                {"das Wohnzimmer","зал","a156","Зал"},
                {"das Schlafzimmer","спальня","a157","Спальня"},
                {"das Badezimmer","ванная","a158","Ванна"},
                {"die Dusche","душ","a159","Душ"},
                {"die Toilette","туалет","a160","Туалет"},
                {"die Küche","кухня","a161","Кухня"},
                {"der Flur","коридор","a162", "Коридор"},
                {"der Balkon","балкон","a163","Балкон"},
                {"der Fußboden","пол","a164", "Підлога"},
                {"die Decke","потолок; одеяло","a165","Стеля; ковдра"},
                {"die Wand","стена","a166","Стіна"},
                {"die Treppe","лестница","a167","Сходи; драбина"},
                {"die Tür","дверь","a168", "Двері"},
                {"das Fenster","окно","a169", "Вікно"},
                {"das Fensterbrett","подоконник","a170","Підвіконня"},
                {"die Gardine","занавес(ка), штора","a171","Завіса(ка), штора"},
                {"der Schalter","выключатель; окошко (кассы)","a172","Вимикач; вікно (каси)"},
                {"die Steckdose","розетка","a173","Розетка"},
                {"der Wasserhahn","(водопроводный) кран","a174","(водопровідний) кран"},
                {"das Rohr","труба","a175","Труба"},
        };

        for (int i = 0; i < allCurrentLesson7.length; i++) {
            addLessonData(allCurrentLesson7[i][0],allCurrentLesson7[i][1],allCurrentLesson7[i][3],allCurrentLesson7[i][2],7);
        }


        String [][] allCurrentLesson8 = {
                {"der Schornstein","дымовая труба","a176","Димова труба"},
                {"die Möbel","мебель","a177", "Меблі"},
                {"der Tisch","стол","a178","Стіл"},
                {"der Stuhl","стул","a179","Стул"},
                {"der Sessel","кресло","a180","Крісло"},
                {"die Couch [кáуч]","диван","a181","Диван"},
                {"das Bett","кровать","a182","Ліжко"},
                {"der Schrank","шкаф","a183","Шкаф"},
                {"das Regal","полка","a184","Полиця"},
                {"der Spiegel","зеркало","a185","Дзеркало"},
                {"der Teppich","ковер","a186","Килим"},
                {"der Kühlschrank","холодильник","a187","Холодильник"},
                {"die Mikrowelle","микроволновка","a188","Мікрохвильова піч"},
                {"der Ofen","печь, духовка","a189","Піч, духовка"},
                {"der Herd","кухонная плита","a190","Кухонна плита"},
                {"die Lebensmittel","еда, продукты","a191","Їжа, продукти"},
                {"das Brot","хлеб","a192", "Хліб"},
                {"die Butter","сливочное масло","a193","Вершкове масло"},
                {"das Öl","растительное масло","a194","Рослинна олія"},
                {"der Käse","сыр","a195","Сир"},
                {"die Wurst","колбаса","a196","Ковбаса"},
                {"das Würstchen","сосиска","a197","Сосиска"},
                {"der Schinken","ветчина","a198","Шинка"},
                {"das Fleisch","мясо","a199", "М'ясо"},
                {"das Rindfleisch","говядина","a200","Яловичина"},
        };

        for (int i = 0; i < allCurrentLesson8.length; i++) {
            addLessonData(allCurrentLesson8[i][0],allCurrentLesson8[i][1],allCurrentLesson8[i][3],allCurrentLesson8[i][2],8);
        }


        String [][] allCurrentLesson9 = {
                {"das Schweinefleisch","свинина","a201","Свинина"},
                {"das Lammfleisch","баранина","a202", "Баранина"},
                {"das Hähnchen","курица (мясо)","a203","Курка (м'ясо)"},
                {"das Kotelett","котлета, отбивная","a204", "Котлета, відбивна"},
                {"der Fisch","рыба","a205","Риба"},
                {"das Ei","яйцо","a206","Яйце"},
                {"der Salat","салат","a207","Салат"},
                {"die Pilze","грибы","a208","Гриби"},
                {"der Mais","кукуруза","a209","Кукурудза"},
                {"der Brei","каша","a210","Каша"},
                {"die Haferflocken","овсянка","a211","Вівсянка"},
                {"die Suppe","суп","a212","Суп"},
                {"das belegte Brötchen","бутерброд","a213","Бутерброд"},
                {"der Reis","рис","a214","Рис"},
                {"die Nudeln","лапша","a215","Лапша"},
                {"das Mehl","мука","a216","Мука"},
                {"das Gewürz","специя, пряность","a217","Спеція, пряність"},
                {"der Pfeffer","перец","a218","Перець"},
                {"das Salz","соль","a219","Сіль"},
                {"die Zwiebel","лук (репчатый)","a220","Цибуля (ріпчаста)"},
                {"der Knoblauch","чеснок","a221","Часник"},
                {"die Soße","соус","a222","Соус"},
                {"das Gemüse","овощи","a223", "Овочі"},
                {"die Kartoffeln","картофель","a224", "Картопля"},
                {"die Mohrrübe","морковь","a225","Морква"},
        };

        for (int i = 0; i < allCurrentLesson9.length; i++) {
            addLessonData(allCurrentLesson9[i][0],allCurrentLesson9[i][1],allCurrentLesson9[i][3],allCurrentLesson9[i][2],9);
        }


        String [][] allCurrentLesson10 = {
                {"die Zuckerrübe","свекла","a226","Буряк"},
                {"die Tomate","помидор","a227","Помідор"},
                {"die Gurke","огурец","a228","Огірок"},
                {"der Kohl","капуста","a229","Капуста"},
                {"die Zucchini ","кабачок","a230", "цукіні; кабачок"},
                {"die Aubergine","баклажан","a231","Баклажан"},
                {"die Bohnen","бобы","a232", "Боби"},
                {"die Erbsen","горох","a233","Горох"},
                {"die Nuss","орех","a234","Горіх"},
                {"das Obst","фрукты","a235","Фрукти"},
                {"der Apfel","яблоко","a236","Яблуко"},
                {"die Birne","груша","a237","Груша"},
                {"die Banane","банан","a238","Банан"},
                {"die Beere","ягода","a239","Ягода"},
                {"die Erdbeere","клубника, земляника","a240","Полуниця, суниця"},
                {"die Himbeere","малина","a241","Малина"},
                {"die Kirsche","вишня","a242","Вишня"},
                {"die Pflaume","слива","a243","Слива"},
                {"die Trauben","виноград","a244","Виноград"},
                {"die Aprikose","абрикос","a245", "Абрикос"},
                {"der Pfirsich","персик","a246","Персик"},
                {"die Melone","дыня","a247","Диня"},
                {"die Wassermelone","арбуз","a248","Кавун"},
                {"der Kürbis","тыква","a249","Гарбуз"},
                {"die Orange","апельсин","a250","Апельсин"},
        };

        for (int i = 0; i < allCurrentLesson10.length; i++) {
            addLessonData(allCurrentLesson10[i][0],allCurrentLesson10[i][1],allCurrentLesson10[i][3],allCurrentLesson10[i][2],10);
        }


        String [][] allCurrentLesson11 = {
                {"die Mandarine","мандарин","a251","Мандарин"},
                {"die Zitrone","лимон","a252","Лимон"},
                {"die Ananas","ананас","a253","Ананас"},
                {"der Zucker","сахар","a254", "Цукор"},
                {"der Honig","мёд","a255","Мед"},
                {"die Marmelade","варенье","a256","Варення"},
                {"der Kuchen","торт, пирог","a257", "Торт, пиріг"},
                {"das Brötchen","булочка","a258","Булочка"},
                {"das Gebäck","печенье","a259","Печиво"},
                {"die Süßigkeiten","конфеты, сладости","a260", "Цукерки, солодощі"},
                {"das Eis","мороженое; лёд","a261", "Морозиво; лід"},
                {"die Schokolade","шоколад","a262","Шоколад"},
                {"das Wasser","вода","a263","Вода"},
                {"das Sodawasser","газировка","a264", "Газована вода"},
                {"der Saft","сок","a265", "Сік"},
                {"der Wein","вино","a266","Вино"},
                {"der Tee","чай","a267","Чай"},
                {"der Kaffee","кофе","a268","Кава"},
                {"die Milch","молоко","a269","Молоко"},
                {"die Sahne","сливки","a270","Вершки"},
                {"der Joghurt","йогурт","a271","Йогурт"},
                {"der Quark","творог","a272","Сир м'який"},
                {"das Geschirr","посуда","a273","Посуд"},
                {"die Tasse","чашка","a274","Чашка"},
                {"das Glas","стакан; стекло","a275","Стакан;скло"},
        };

        for (int i = 0; i < allCurrentLesson11.length; i++) {
            addLessonData(allCurrentLesson11[i][0],allCurrentLesson11[i][1],allCurrentLesson11[i][3],allCurrentLesson11[i][2],11);
        }


        String [][] allCurrentLesson12 = {
                {"der Becher","кружка","a276","Кружка"},
                {"der Teller","тарелка","a277","Тарілка"},
                {"der Löffel","ложка","a278","Ложка"},
                {"die Gabel","вилка","a279","Виделка"},
                {"das Messer","нож","a280","Ніж"},
                {"die Untertasse","блюдце","a281","Блюдце"},
                {"die Flasche","бутылка","a282","Пляшка"},
                {"die Serviette","салфетка","a283","Серветка"},
                {"der Topf","кастрюля; горшок","a284", "Каструля; горщик"},
                {"die Pfanne","сковородка","a285","Сковородка"},
                {"der Kessel","чайник; котел","a286", "Чайник; котел"},
                {"der Nachmittag","время после полудня","a314","Час після полудня"},
                {"das Frühstück","завтрак","a288","Сніданок"},
                {"das Mittagessen","обед","a289", "Обід"},
                {"das Abendessen","ужин","a290","Вечеря"},
                {"der Transport","транспорт","a291","Транспорт"},
                {"das Flugzeug","самолет","a292", "Літак"},
                {"das Auto","автомобиль","a293","Автомобіль"},
                {"die Straßenbahn","трамвай","a294","Трамвай"},
                {"der Bus","автобус","a295","Автобус"},
                {"der Zug","поезд","a296","Потяг"},
                {"das Schiff","корабль","a297","Корабель"},
                {"das Fahrrad","велосипед","a298", "Велосипед"},
                {"die Zeit","время","a299","Час"},
                {"die Minute","минута","a300", "Хвилина"},
        };

        for (int i = 0; i < allCurrentLesson12.length; i++) {
            addLessonData(allCurrentLesson12[i][0],allCurrentLesson12[i][1],allCurrentLesson12[i][3],allCurrentLesson12[i][2],12);
        }


        String [][] allCurrentLesson13 = {
                {"die Stunde","час","a301", "Година"},
                {"die Woche","неделя","a302","Тиждень"},
                {"das Jahr","год","a303","Рік"},
                {"das Jahrhundert","век, столетие","a304", "Вік, століття"},
                {"das Mal","раз","a305","Раз"},
                {"vorgestern","позавчера","a306","Позавчора"},
                {"gestern","вчера","a307","Вчора"},
                {"heute","сегодня","a308","Сьогодні"},
                {"morgen","завтра","a309","Завтра"},
                {"übermorgen","послезавтра","a310","Післязавтра"},
                {"der Tag","день","a311","День"},
                {"der Morgen","утро","a312","Ранок"},
                {"der Nachmittag","время до полудня","a313", "Час до полудня"},
                {"die Mahlzeit","принятие пищи, еда","a287", "Прийняття їжі,їжа"},
                {"der Abend","вечер","a315","Вечір"},
                {"die Nacht","ночь","a316","Ніч"},
                {"der Montag","понедельник","a317","Понеділок"},
                {"der Dienstag","вторник","a318","Вівторок"},
                {"der Mittwoch","среда","a319","Середа"},
                {"der Donnerstag","четверг","a320","Четвер"},
                {"der Freitag","пятница","a321","П'ятниця"},
                {"der Samstag","суббота","a322","Субота"},
                {"der Sonntag","воскресенье","a323", "Неділя"},
                {"der Monat","месяц","a324","Місяць"},
                {"der Januar","январь","a325","Січень"},
        };

        for (int i = 0; i < allCurrentLesson13.length; i++) {
            addLessonData(allCurrentLesson13[i][0],allCurrentLesson13[i][1],allCurrentLesson13[i][3],allCurrentLesson13[i][2],13);
        }


        String [][] allCurrentLesson14 = {
                {"der Februar","февраль","a326","Лютий"},
                {"der März","март","a327","Березень"},
                {"der April","апрель","a328","Квітень"},
                {"der Mai","май","a329","Травень"},
                {"der Juni","июнь","a330","Червень"},
                {"der Juli","июль","a331","Липень"},
                {"der August","август","a332","Серпень"},
                {"der September","сентябрь","a333","Вересень"},
                {"der Oktober","октябрь","a334","Жовтень"},
                {"der November","ноябрь","a335","Листопад"},
                {"der Dezember","декабрь","a336","Грудень"},
                {"die Jahreszeit","время года","a337","Пора року"},
                {"der Frühling","весна","a338","Весна"},
                {"der Sommer","лето","a339","Літо"},
                {"der Herbst","осень","a340","Осінь"},
                {"der Winter","зима","a341","Зима"},
                {"das Fest","праздник","a342","Свято"},
                {"das Weihnachten","Рождество","a343","Різдво"},
                {"das Ostern","Пасха","a344","Пасха"},
                {"der Geburtstag","день рождения","a345","День народження"},
                {"das Formular","формуляр, бланк","a346","Формуляр, бланк"},
                {"der Name","имя, фамилия; название;","a347","Iм'я, прізвище; назва;"},
                {"der Vorname","имя","a348","Iм'я"},
                {"der Nachname","фамилия","a349", "Прізвище"},
                {"der Mädchenname","девичья фамилия","a350","Дівоче прізвище"},
        };

        for (int i = 0; i < allCurrentLesson14.length; i++) {
            addLessonData(allCurrentLesson14[i][0],allCurrentLesson14[i][1],allCurrentLesson14[i][3],allCurrentLesson14[i][2],14);
        }


        String [][] allCurrentLesson15 = {
                {"der Geburtsdatum","дата рождения","a351","Дата народження"},
                {"der Geburtsort","место рождения","a352","Місце народження"},
                {"die Adresse","адрес","a353","Адреса"},
                {"der Familienstand","семейное положение","a354","Сімейний стан"},
                {"ledig","холостой, незамужняя","a355","Холостий, незаміжня"},
                {"verheiratet","женатый / замужняя","a356","Одружений/заміжня"},
                {"geschieden","разведенный","a357","Розведений"},
                {"verwitwet","овдовевший","a358","Овдовілий"},
                {"die Sache","вещь","a359","Річ"},
                {"der Kugelschreiber","ручка","a360","Ручка"},
                {"der Bleistift","карандаш","a361", "Олівець"},
                {"das Buch","книга","a362","Книга"},
                {"das Heft","тетрадь","a363","Зошит"},
                {"das Notizbuch","блокнот","a364","Блокнот"},
                {"die Notiz","пометка, запись","a365","Помітка, запис"},
                {"das Wörterbuch","словарь","a366","Словник"},
                {"der Buchstabe","буква","буква","a367","Літера"},
                {"der Brief","письмо","a368","Лист"},
                {"der Umschlag","конверт","a369", "Конверт"},
                {"das Papier","бумага","a370", "Папір"},
                {"die Zeitung","газета","a371","Газета"},
                {"die Zeitschrift","журнал","a372","Журнал"},
                {"das Telefon","телефон","a373","Телефон"},
                {"das Handy [хэнди]","сотовый телефон","a374","Стільниковий телефон"},
                {"die Uhr","часы","a375","Годинник"},
        };

        for (int i = 0; i < allCurrentLesson15.length; i++) {
            addLessonData(allCurrentLesson15[i][0],allCurrentLesson15[i][1],allCurrentLesson15[i][3],allCurrentLesson15[i][2],15);
        }


        String [][] allCurrentLesson16 = {
                {"der Kamm","расчёска","a376", "Гребінець"},
                {"der Fernseher","телевизор","a377","Телевізор"},
                {"das Bűgeleisen","утюг","a378","Праска"},
                {"die Seife","мыло","a379","Мило"},
                {"das Radio","радио","a380","Радіо"},
                {"die Tasche","сумка","a381","Сумка"},
                {"der Rucksack","рюкзак","a382","Рюкзак"},
                {"die Karte","карта","a383","Карта"},
                {"die Postkarte","открытка","a384","Листівка"},
                {"der Koffer","чемодан","a385","Валіза"},
                {"das Geschenk","подарок","a386","Подарунок"},
                {"die Kamera","камера","a387","Камера"},
                {"der Fotoapparat","фотоаппарат","a388","Фотоапарат"},
                {"die Vase","ваза","a389","Ваза"},
                {"das Taschentuch","носовой платок","a390","Носовичок"},
                {"der Ball","мяч","a391", "М'яч"},
                {"der Luftballon","воздушный шар(ик)","a392","Повітряна кулька"},
                {"das Spielzeug","игрушка","a393","Iграшка"},
                {"die Fahrkarte","билет (на поезд)","a394","Квиток на поїзд"},
                {"der Fahrschein","билет (на городской транспорт)","a395", "Квиток (на міський транспорт)"},
                {"das Ticket","билет (на самолёт)","a396","Квиток на літак"},
                {"das Gepäck","багаж","a397","Багаж"},
                {"die Batterie","батарейка, аккумулятор","a398","Батарейка, акумулятор"},
                {"die Eimer","ведро","a399","Відро"},
                {"der Leine","веревка","a400","Мотузка"},
        };

        for (int i = 0; i < allCurrentLesson16.length; i++) {
            addLessonData(allCurrentLesson16[i][0],allCurrentLesson16[i][1],allCurrentLesson16[i][3],allCurrentLesson16[i][2],16);
        }


        String [][] allCurrentLesson17 = {
                {"die Tafel","доска (школьная)","a401","Дошка шкільна"},
                {"das Brett","доска, брусок","a402","Дошка, брусок"},
                {"der Kalender","календарь","a403","Календар"},
                {"der Laptop [лэптоп]","ноутбук","a404","Ноутбук"},
                {"die Bürste","щетка","a405","Щітка"},
                {"die Pinsel","кисть, кисточка","a406","Пензлик"},
                {"die Tastatur","клавиатура","a407","Клавіатура"},
                {"die Taste","клавиша","a408","Клавіша"},
                {"der Schlüssel","ключ","a409","Ключ"},
                {"das Rad","колесо","a410","Колесо"},
                {"das Lenkrad","руль","a411","Кермо"},
                {"der Kofferraum","багажник","a412","Багажник"},
                {"das Benzin","бензин","a413","Бензин"},
                {"die Geldbörse","кошелек","a414","Гаманець"},
                {"die Brieftasche","бумажник","a415","Бумажник"},
                {"die Lampe","лампа","a416", "Лампа"},
                {"das Lineal","линейка","a417", "Лінійка"},
                {"die Schaufel","лопата","a418", "Лопата"},
                {"die Maschine","машина; механизм","a419","Машина; механізм"},
                {"der Hammer","молоток","a420","Молоток"},
                {"die Schere","ножницы","a421","Ножиці"},
                {"die Brille","очки","a422","Окуляри"},
                {"das Paket","посылка","a423","Посилка"},
                {"der Stock","палка","a424","Палка"},
                {"der Klebstoff","клей","a425","Клей"},
        };

        for (int i = 0; i < allCurrentLesson17.length; i++) {
            addLessonData(allCurrentLesson17[i][0],allCurrentLesson17[i][1],allCurrentLesson17[i][3],allCurrentLesson17[i][2],17);
        }


        String [][] allCurrentLesson18 = {
                {"das Handtuch","полотенце","a426","Рушник"},
                {"der Draht","проволока","a427","Дріт,проволка"},
                {"das Kabel","провод, кабель","a428","Провід, кабель"},
                {"die Seite","страница","a429","Сторінка"},
                {"die Taschenlampe","карманный фонарь","a430","Кишеньковий ліхтар"},
                {"der Kasten","ящик","a431","Ящик"},
                {"die Kiste","коробка","a432","Коробка"},
                {"das Laken","простыня","a433","Простирадло"},
                {"das Kissen","подушка","a434","Подушка"},
                {"die Kleidung","одежда","a435","Одяг"},
                {"die Schuhe","обувь, туфли","a436","Взуття, туфлі"},
                {"die Stiefel","сапоги","a437","Чоботи"},
                {"die Turnschuhe","кроссовки","a438", "Кросівки"},
                {"der Mantel","пальто","a439","Пальто"},
                {"das Kleid","платье","a440","Плаття, Сукня"},
                {"der Anzug","костюм","a441","Костюм"},
                {"das Hemd","рубашка","a442","Сорочка"},
                {"die Bluse","блузка","a443","Блузка"},
                {"der Rock","юбка","a444","Спідниця"},
                {"die Handschuhe","перчатки","a445","Перчатки"},
                {"die Fäustlinge","варежки","a446","Рукавички"},
                {"der Hut","шляпа","a447","Капелюх"},
                {"die Mütze","шапка; кепка","a448","Шапка; кепка"},
                {"die Jacke","Куртка","a449","Куртка"},
                {"die Strickjacke","кофта","a450","Кофта"},
        };

        for (int i = 0; i < allCurrentLesson18.length; i++) {
            addLessonData(allCurrentLesson18[i][0],allCurrentLesson18[i][1],allCurrentLesson18[i][3],allCurrentLesson18[i][2],18);
        }


        String [][] allCurrentLesson19 = {
                {"das Sakko","пиджак","a451","Піджак"},
                {"der Schal","шарф","a452","Шарф"},
                {"die Socke","носок","a453","Шкарпетка"},
                {"der Pullover","свитер","a454","Светр"},
                {"das T-Shirt","футболка","a455","Футболка"},
                {"die Kravatte","галстук","a456","Галстук"},
                {"die Hose","брюки, штаны","a457","Брюки Штани"},
                {"die Shorts [шортс]","шорты","a458","Шорти"},
                {"die Strumpfhose","колготки","a459","Колготки"},
                {"die Strümpfe","чулки","a460","Панчохи"},
                {"die Jeans [джинз]","джинсы","a461","Джинсы"},
                {"die Kapuze","капюшон","a462","Капюшон"},
                {"der Gürtel","ремень","a463", "Ремінь"},
                {"die Unterwäsche","нижнее белье","a464","Нижня білизна"},
                {"die Unterhose","трусы","a465","Труси"},
                {"der Büstenhaĺter","бюстгальтер","a466", "Бюстгальтер"},
                {"der Körper","тело","a467", "Тіло"},
                {"der Kopf","голова","a468","Голова"},
                {"das Gesicht","лицо","a469","Лице"},
                {"die Stirn","лоб","a470","Лоб"},
                {"die Nase","нос","a471", "Ніс"},
                {"das Ohr","ухо","a472","Вухо"},
                {"der Mund","рот","a473","Рот"},
                {"der Hals","горло; шея","a474","Горло; шия"},
                {"das Auge","глаз","a475","Око"},
        };

        for (int i = 0; i < allCurrentLesson19.length; i++) {
            addLessonData(allCurrentLesson19[i][0],allCurrentLesson19[i][1],allCurrentLesson19[i][3],allCurrentLesson19[i][2],19);
        }


        String [][] allCurrentLesson20 = {
                {"die Augenbraue","бровь","a476","Бров"},
                {"die Lippen","губы","a477", "Губи"},
                {"der Zahn","зуб","a478","Зуб"},
                {"das Haar","волос(ы)","a479", "Волосся"},
                {"der Schnurrbart","усы","a480","Вуса"},
                {"der Bart","борода","a481","Борода"},
                {"die Wange","щека","a482","Щока"},
                {"das Kinn","подбородок","a483","Підборіддя"},
                {"die Schulter","плечо","a484","Плече"},
                {"die Brust","грудь","a485","Груди"},
                {"das Herz","сердце","a486","Серце"},
                {"der Bauch","живот","a487", "Живіт"},
                {"der Rücken","спина","a488", "Спина"},
                {"das Handgelenk","запястье","a489","Зап'ясті"},
                {"die Hand","рука, кисть (руки)","a490","Рука, кисть (руки)"},
                {"der Arm","рука (вся)","a491","Рука (вся)"},
                {"der Finger","палец (руки)","a492","Палець (руки)"},
                {"der Nagel","ноготь; гвоздь","a493","Ніготь; цвях"},
                {"der Ellenbogen","локоть","a494","Лікоть"},
                {"das Bein","нога","a495","Нога"},
                {"das Knie","колено","a496","Коліно"},
                {"der Fuß","нога, ступня; подножие","a497", "Нога, ступня; підніжжя"},
                {"die Ferse","пятка","a498","П'ятка"},
                {"die Zehe","палец (ноги)","a499","Палець (ноги)"},
                {"der Knochen","кость","a500","Кістка"},
        };

        for (int i = 0; i < allCurrentLesson20.length; i++) {
            addLessonData(allCurrentLesson20[i][0],allCurrentLesson20[i][1],allCurrentLesson20[i][3],allCurrentLesson20[i][2],20);
        }


        String [][] allCurrentLesson21 = {
                {"die Gesundheit","здоровье","a501","здоров'я"},
                {"gesund","здоровый","a502","Здоровий"},
                {"krank","больной","a503", "Хворий"},
                {"die Krankheit","болезнь","a504", "Хвороба"},
                {"das Fieber","жар, (высокая) температура","a505","Жар, (висока) температура"},
                {"der Husten","кашель","a506","Кашель"},
                {"der Schnupfen","насморк","a507","Нежить"},
                {"niesen","чихать","a508", "Чхати"},
                {"der Schmerz","боль","a509","Біль"},
                {"die Kopfschmerzen","головная боль","a510","Головний біль"},
                {"weh tun","болеть","a511","Хворіти"},
                {"die Grippe","грипп","a512","Грип"},
                {"die Erkältung","простуда","a513", "Застуда"},
                {"die Prellung","синяк, ушиб","a514","Синяк, забій"},
                {"das Ereignis","событие","a515", "Подія"},
                {"die Geburt","рождение","a516", "Народження"},
                {"das Spiel","игра","a517","Гра"},
                {"der Unterricht","занятие (урок)","a518", "Заняття (урок)"},
                {"der Urlaub","отпуск","a519", "Відпустка"},
                {"die Ferien","каникулы","a520", "Канікули"},
                {"die Party ","вечеринка","a521","Вечірка"},
                {"das Treffen","встреча","a522", "Зустріч"},
                {"die Versammlung","собрание","a523", "Збори"},
                {"die Hochzeit","свадьба","a524","Весілля"},
                {"die Verhandlungen","переговоры","a525","Переговори"},
        };

        for (int i = 0; i < allCurrentLesson21.length; i++) {
            addLessonData(allCurrentLesson21[i][0],allCurrentLesson21[i][1],allCurrentLesson21[i][3],allCurrentLesson21[i][2],21);
        }


        String [][] allCurrentLesson22 = {
                {"die Reise","поездка, путешествие","a526","Поїздка, подорож"},
                {"der Tod","смерть","a527","Смерть"},
                {"das Wetter","погода","a528","Погода"},
                {"die Sonne","солнце","a529","Сонце"},
                {"der Mond","луна","a530", "Луна"},
                {"der Wind","ветер","a531","Вітер"},
                {"der Nebel","туман","a532","Туман"},
                {"der Regen","дождь","a533","Дощ"},
                {"der Schnee","снег","a534","Сніг"},
                {"der Himmel","небо","a535","Небо"},
                {"die Wolke","облако","a536","Хмара"},
                {"die Luft","воздух","a537","Повітря"},
                {"die Temperatur","температура","a538","Температура"},
                {"der Grad","градус","a539","Градус"},
                {"die Kunst","искусство","a540","Мистецтво"},
                {"die Musik","музыка","a541","Музика"},
                {"das Lied","песня","a542","Пісня"},
                {"die Literatur","литература","a543","Література"},
                {"die Geschichte","рассказ, история","a544", "Оповідання, історія"},
                {"der Film","фильм; пленка","a545","Фільм; плівка"},
                {"die Skulptur","скульптура","a546", "Скульптура"},
                {"das Bild","картина","a547","Картина"},
                {"das Foto","фото","a548","Фото"},
                {"die Werbung","реклама","a549","Реклама"},
                {"der Einkäufe","покупки","a550","Покупки"},
        };

        for (int i = 0; i < allCurrentLesson22.length; i++) {
            addLessonData(allCurrentLesson22[i][0],allCurrentLesson22[i][1],allCurrentLesson22[i][3],allCurrentLesson22[i][2],22);
        }


        String [][] allCurrentLesson23 = {
                {"die Größe","размер","a551", "Розмір"},
                {"der Preis","цена","a552","Ціна"},
                {"das Geld","деньги","a553","Гроші"},
                {"das Bargeld","наличные (деньги)","a554","Готівкові гроші"},
                {"der Rabatt","скидка","a555","Знижка"},
                {"das Konto","счет (банковский)","a556","Рахунок (банківський)"},
                {"die Rechnung","счёт (для оплаты)","a557","Рахунок для оплати)"},
                {"das Trinkgeld","чаевые","a558", "Чайові"},
                {"das Messen","измерение","a559", "Вимірювання"},
                {"die Entfernung","расстояние","a560", "Відстань"},
                {"die Distanz","дистанция","a561", "Дистанція"},
                {"die Länge","длина","a562","Довжина"},
                {"die Höhe","высота","a563","Висота"},
                {"die Tiefe","глубина","a564","Глибина"},
                {"ihr","вы","a659","ви"},
                {"die Geschwindigkeit","скорость","a566","Швидкість"},
                {"der Kilometer","километр","a567","Кілометр"},
                {"das Kilogramm","килограмм","a568","Кілограм"},
                {"das Pfund","фунт","a569","Фунт"},
                {"das Gefühl","чувство","a570", "Почуття"},
                {"der Spaß","веселье","a571","Веселощi"},
                {"die Freude","радость","a572", "Радість"},
                {"die Angst","страх","a573", "Страх"},
                {"die Traurigkeit","печаль","a574","Печаль"},
                {"die Leidenschaft","страсть, страстность","a575", "Пристрасть, пристрасність"},
        };

        for (int i = 0; i < allCurrentLesson23.length; i++) {
            addLessonData(allCurrentLesson23[i][0],allCurrentLesson23[i][1],allCurrentLesson23[i][3],allCurrentLesson23[i][2],23);
        }


        String [][] allCurrentLesson24 = {
                {"das Vergnügen","удовольствие","a576", "Задоволення"},
                {"das Glück","счастье","a577","Щастя"},
                {"der Frieden","мир (покой)","a578", "Мир (спокій)"},
                {"lieben","любить","a579","Любити"},
                {"hassen","ненавидеть","a580", "Ненавидіти"},
                {"die Welt","мир (планета)","a581", "Світ (планета)"},
                {"die Seele","душа","a582", "Душа"},
                {"das Leben","жизнь","a583","Життя"},
                {"das Wissen","знание","a584", "Знання"},
                {"die Aufgabe","задача, задание","a585", "Задача, завдання"},
                {"die Übung","упражнение","a586", "Вправа"},
                {"das Problem","проблема","a587", "Проблема"},
                {"die Gelegenheit","(благоприятная) возможность","a588","(Сприятлива) можливість"},
                {"die Fähigkeit","способность","a589","Здатність"},
                {"die Schönheit","красота","a590","Краса"},
                {"die Gefahr","опасность","a591", "Небезпека"},
                {"die Erfahrung","опыт","a592", "досвід"},
                {"das Gedächtnis","память","a593","Пам'ять"},
                {"die Erinnerung","воспоминание","a594","Спогад"},
                {"der Nutzen","польза","a595", "Користь"},
                {"ihr","её; их","a665","її; їх"},
                {"der Gewinn","выигрыш; прибыль, доход","a597", "Виграш; прибуток, дохід"},
                {"die Gewohnheit","привычка","a598","Звичка"},
                {"der Grund","причина; грунт, земля","a599","Причина; грунт, земля"},
                {"die Konsequenz","(по)следствие","a600","(за)наслідком"},
        };

        for (int i = 0; i < allCurrentLesson24.length; i++) {
            addLessonData(allCurrentLesson24[i][0],allCurrentLesson24[i][1],allCurrentLesson24[i][3],allCurrentLesson24[i][2],24);
        }


        String [][] allCurrentLesson25 = {
                {"die Bedeutung","значение","a601", "Значення"},
                {"das Mittel","средство","a602", "Засіб"},
                {"die Anstrengung","усилие","a603", "Зусилля"},
                {"der Erfolg","успех","a604","Успіх"},
                {"das Ziel","цель","a605","Мета"},
                {"das Wunder","чудо","a606","Чудо"},
                {"die Wissenschaft","наука","a607", "Наука"},
                {"die Sprache","язык","a608","Мова"},
                {"das Wort","слово","a609","Слово"},
                {"das Feuer","огонь","a610", "Вогонь"},
                {"der Fall","случай; дело; падение","a611", "Випадок; справа; падіння"},
                {"der Umstand","обстоятельство","a612", "Обставина"},
                {"der Gedanke","мысль","a613","Думка"},
                {"die Wahl","выбор","a614", "Вибір"},
                {"die Erlaubnis","разрешение","a615","Дозвіл"},
                {"die Lieferung","доставка; поставка","a616", "Доставка; постачання"},
                {"die Leistung","достижение, успех","a617", "Досягнення, успіх"},
                {"die Pflicht","обязанность","a618", "Обов'язок"},
                {"die Verzögerung","задержка, промедление","a619","Затримка, зволікання"},
                {"die Beziehung","отношение","a620","Ставлення"},
                {"die Note","оценка, отметка, знак; нота","a621", "Оцінка, позначка, знак; нота"},
                {"der Fehler","ошибка","a622","Помилка"},
                {"das Benehmen","поведение","a623","Поведінка"},
                {"die Einladung","приглашение","a624","Запрошення"},
                {"die Entwicklung","развитие","a625", "Розвиток"},
        };

        for (int i = 0; i < allCurrentLesson25.length; i++) {
            addLessonData(allCurrentLesson25[i][0],allCurrentLesson25[i][1],allCurrentLesson25[i][3],allCurrentLesson25[i][2],25);
        }


        String [][] allCurrentLesson26 = {
                {"die Entscheidung","решение, принятие решения","a626", "Рішення, прийняття рішення"},
                {"die Lösung","решение (проблемы)","a627","Рішення проблеми"},
                {"der Rat","совет","a628","Порада"},
                {"die Vereinbarung","соглашение; согласие","a629","Угода; згода"},
                {"sie","она","a656","вона"},
                {"die Liste","список","a631","Список,перелік"},
                {"der Streit","спор","a632","Суперечка"},
                {"der Test","тест, испытание","a633", "Тест, випробування"},
                {"die Prüfung","экзамен","a634","Іспит"},
                {"der Schritt","шаг","a635", "Крок"},
                {"das Stück","кусок; штука","a636", "Шматок; штука"},
                {"der Bereich","область, сфера","a637","Область, сфера"},
                {"das Paar","пара","a638","Пара"},
                {"die Oberfläche","поверхность","a639", "Поверхня"},
                {"die Ordnung","порядок","a640","Порядок"},
                {"die Frage","вопрос","a641", "Питання"},
                {"was","что","a642","Що"},
                {"wer","кто","a643","Хто"},
                {"wo","где","a644","Де"},
                {"wohin","куда","a645","Куди"},
                {"woher","откуда","a646","Звідки"},
                {"wie","как","a647","Як"},
                {"welcher","какой","a648","Який"},
                {"warum","почему","a649","Чому"},
                {"wozu","зачем","a650","Навіщо"},
        };

        for (int i = 0; i < allCurrentLesson26.length; i++) {
            addLessonData(allCurrentLesson26[i][0],allCurrentLesson26[i][1],allCurrentLesson26[i][3],allCurrentLesson26[i][2],26);
        }


        String [][] allCurrentLesson27 = {
                {"wenn","когда; если","a651","Коли; якщо",},
                {"wie viel (wie viele)","сколько","a652", "Скільки"},
                {"ich","я","a653","Я"},
                {"du","ты","a654","ти"},
                {"er","он","a655","він"},
                {"der Vertrag","договор","a630","Договір"},
                {"es","оно","a657","воно"},
                {"wir","мы","a658","ми"},
                {"die Stärke","сила","a565","Сила"},
                {"schätzen","ценить","a1092","Цінувати"},///////////
                {"Sie","Вы","a661","Ви"},
                {"mein","мой","a662","мій"},
                {"dein","твой","a663", "твій"},
                {"sein","его","a664","його"},
                {"der Vorteil","преимущество","a596", "Перевага"},
                {"unser","наш","a666","наш"},
                {"euer","ваш","a667","ваш"},
                {"Ihr","Ваш","a668","Ваш"},
                {"aus","из","a669","з"},
                {"von","от; передает род. падеж; с (такого-то времени)","a670", "Від; передає рід. відмінок; з (такого часу)"},
                {"seit","с (такого-то времени и поныне)","a671", "З (такого часу і понині)"},
                {"zu","к; слишком","a672", "До; занадто"},
                {"in","в; через (такое-то время)","a673","в; через (такий-то час)"},
                {"auf","на","a674","на"},
                {"unter","под","a675","під"},
        };

        for (int i = 0; i < allCurrentLesson27.length; i++) {
            addLessonData(allCurrentLesson27[i][0],allCurrentLesson27[i][1],allCurrentLesson27[i][3],allCurrentLesson27[i][2],27);
        }


        String [][] allCurrentLesson28 = {
                {"hinter","позади","a676","позаду"},
                {"mit","с","a677","з"},
                {"ohne","без","a678","без"},
                {"vor","перед, до; (столько-то времени) назад","a679","перед, до; (стільки часу) назад"},
                {"nach","после; в (такой-то город или страну)","a680","після; в (таке-то місто або країну)"},
                {"zwischen","между","a681", "між"},
                {"neben","возле, около","a682","біля"},
                {"für","для; на (такое-то время)","a683","для; на (такий-то час)"},
                {"während","во время","a684","під час"},
                {"um","вокруг","a685", "навколо"},
                {"wegen","из-за","a686","через (когось, щось)"},
                {"über","над; о; свыше","a687", "над; про; згори"},
                {"gegen","против","a688", "проти"},
                {"unter","под; среди","a689", "під; серед"},
                {"durch","через","a690","через"},
                {"pro","(километров) в (час)","a691","(кілометрів) на (годину)"},
                {"die Zahl","число, цифра; количество","a692","число, цифра; кількість"},
                {"null","ноль","a693","нуль"},
                {"eins","один","a694","один"},
                {"zwei","два","a695","два"},
                {"drei","три","a696","три"},
                {"vier","четыре","a697","чотири"},
                {"fünf","пять","a698","п'ять"},
                {"sechs","шесть","a699","шість"},
                {"sieben","семь","a700","сім"},
        };

        for (int i = 0; i < allCurrentLesson28.length; i++) {
            addLessonData(allCurrentLesson28[i][0],allCurrentLesson28[i][1],allCurrentLesson28[i][3],allCurrentLesson28[i][2],28);
        }


        String [][] allCurrentLesson29 = {
                {"acht","восемь","a701","вісім"},
                {"neun", "девять","a702", "дев'ять"},
                {"zehn","десять","a703","десять"},
                {"elf","одиннадцать","a704","одинадцать"},
                {"zwölf","двенадцать","a705","дванадцать"},
                {"dreizehn","тринадцать","a706","тринадцять"},
                {"vierzehn","четырнадцать","a707","чотирнадцять"},
                {"fünfzehn","пятнадцать","a708","п'ятнадцять"},
                {"sechzehn","шестнадцать","a709","шістнадцять"},
                {"siebzehn","семнадцать","a710","сімнадцять"},
                {"achtzehn","восемнадцать","a711","вісімнадцять"},
                {"neunzehn","девятнадцать","a712","дев'ятнадцять"},
                {"zwanzig","двадцать","a713","двадцать"},
                {"dreißig","тридцать","a714","тридцять"},
                {"vierzig","сорок","a715","сорок"},
                {"fünfzig","пятьдесят","a716","п'ятдесят"},
                {"sechzig","шестьдесят","a717", "шістдесят"},
                {"siebzig","семьдесят","a718","сімдесят"},
                {"achtzig","восемьдесят","a719", "вісімдесят"},
                {"neunzig","девяносто","a720","дев'яносто"},
                {"hundert","сто","a721","сто"},
                {"tausend","тысяча","a722","тисяча"},
                {"der erste","первый","a723", "перший"},
                {"der zweite","второй","a724","другий"},
                {"der dritte","третий","a725","третій"},
        };

        for (int i = 0; i < allCurrentLesson29.length; i++) {
            addLessonData(allCurrentLesson29[i][0],allCurrentLesson29[i][1],allCurrentLesson29[i][3],allCurrentLesson29[i][2],29);
        }


        String [][] allCurrentLesson30 = {
                {"der vierte","четвертый","a726","четвертий"},
                {"die Farbe","цвет, краска","a727", "Колір, фарба"},
                {"schwarz","чёрный","a728","чорний"},
                {"blau","голубой; синий","a729","блакитний; синій"},
                {"braun","коричневый","a730","коричневий"},
                {"grün","зелёный","a731","зелений"},
                {"grau","серый","a732","сірий"},
                {"rot","красный","a733","червоний"},
                {"weiß","белый","a734","білий"},
                {"gelb","желтый","a735","жовтий"},
                {"rosa","розовый","a736","рожевий"},
                {"violett","фиолетовый","a737","Фіолетовий"},
                {"alt","старый","a738","старий"},
                {"jung","молодой","a739","молодий"},
                {"neu","новый","a740","новий"},
                {"groß","большой","a741","великий"},
                {"riesig","огромный","a742","величезний"},
                {"klein","маленький","a743","маленький"},
                {"dick","толстый; густой","a744","товстий; густий"},
                {"dünn","тонкий","a745","тонкий"},
                {"hungrig","голодный","a746","голодний"},
                {"satt","сытый","a747", "ситий"},
                {"voll","полный","a748", "повний"},
                {"leer","пустой","a749","порожній"},
                {"gut","хороший; добрый","a750", "хороший; добрий"},
        };

        for (int i = 0; i < allCurrentLesson30.length; i++) {
            addLessonData(allCurrentLesson30[i][0],allCurrentLesson30[i][1],allCurrentLesson30[i][3],allCurrentLesson30[i][2],30);
        }


        String [][] allCurrentLesson31 = {
                {"ausgezeichnet","отличный","a751","чудовий"},
                {"erstaunlich","удивительный","a752","дивовижний"},
                {"wunderschön","прекрасный, чудесный","a753","прекрасний, чудовий"},
                {"schlecht","плохой","a754", "поганий"},
                {"schrecklich","ужасный","a755", "жахливий"},
                {"früh","ранний; рано","a756","ранній; рано"},
                {"spät","поздний; поздно","a757","пізній; пізно"},
                {"vorig","последний, прошлый","a758","останній, минулий"},
                {"nächst","следующий","a759","наступний"},
                {"frei","свободный","a760", "вільний"},
                {"kostenlos","бесплатный","a761", "безкоштовний"},
                {"beschäftigt","занятый","a762","зайнятий"},
                {"heiß","жаркий; горячий","a763","спекотний; гарячий"},
                {"warm","тёплый","a764","теплий"},
                {"kalt","холодный","a765","холодний"},
                {"kühl","прохладный","a766","прохолодний"},
                {"scharf","острый","a767","гострий"},
                {"stumpf","тупой (нож)","a768", "тупий (ніж)"},
                {"dumm","глупый","a769","дурний"},
                {"klug","умный","a770", "розумний"},
                {"schön","красивый","a771","гарний"},
                {"attraktiv","привлекательный","a772","привабливий"},
                {"hübsch","симпатичный","a773","симпатичний"},
                {"hässlich","уродливый, безобразный, отвратительный","a774", "потворний,огидний"},
                {"hoch","высокий","a775","високий"},
        };

        for (int i = 0; i < allCurrentLesson31.length; i++) {
            addLessonData(allCurrentLesson31[i][0],allCurrentLesson31[i][1],allCurrentLesson31[i][3],allCurrentLesson31[i][2],31);
        }


        String [][] allCurrentLesson32 = {
                {"niedrig","низкий","a776","низький"},
                {"lang","длинный; долгий","a777", "довгий"},
                {"kurz","короткий","a778","короткий"},
                {"schwer","тяжёлый","a779","важкий"},
                {"leicht","лёгкий","a780", "легкий"},
                {"schwierig","трудный, тяжелый","a781", "тяжкий, важкий"},
                {"einfach","простой","a782","простий"},
                {"dunkel","тёмный","a783","темний"},
                {"hell","светлый","a784", "світлий"},
                {"teuer","дорогой (о цене)","a785","дорогий (про ціну)"},
                {"lieb","дорогой, милый","a786","дорогий, милий"},
                {"billig","дешёвый","a787","дешевий"},
                {"arm","бедный","a788","бідний"},
                {"reich","богатый","a789","богатий"},
                {"gerade","прямой; прямо","a790","прямий; прямо"},
                {"links","cлева","a791","ліворуч"},
                {"rechts","справа","a792", "праворуч"},
                {"falsch","неправильный","a793", "неправильний"},
                {"schnell","быстрый","a794","швидкий"},
                {"langsam","медленный","a795", "повільний"},
                {"weich","мягкий","a796","м'який"},
                {"hart","твёрдый; жесткий; трудный","a797", "твердий; жорсткий; важкий"},
                {"traurig","печальный","a798","сумний"},
                {"froh","радостный","a799", "радісний"},
                {"glücklich","счастливый","a800","щасливий"},
        };

        for (int i = 0; i < allCurrentLesson32.length; i++) {
            addLessonData(allCurrentLesson32[i][0],allCurrentLesson32[i][1],allCurrentLesson32[i][3],allCurrentLesson32[i][2],32);
        }


        String [][] allCurrentLesson33 = {
                {"lustig","веселый","a801","веселий"},
                {"böse","сердитый, злой","a802","сердитий, злий"},
                {"höflich","вежливый","a803","ввічливий"},
                {"grob","грубый","a804","грубий"},
                {"zart","нежный","a805","ніжний"},
                {"mutig","смелый","a806","сміливий"},
                {"feige","трусливый","a807","боягузливий"},
                {"scheu","застенчивый","a808","сором'язливий"},
                {"fleißig","прилежный","a809","старанний"},
                {"faul","ленивый","a810","ледачий"},
                {"nützlich","полезный","a811","корисний"},
                {"nutzlos","бесполезный","a812","марний"},
                {"stark","сильный; крепкий","a813", "сильний; міцний"},
                {"schwach","слабый","a814","слабкий"},
                {"laut","громкий, шумный","a815","гучний, галасливий"},
                {"leise","тихий, негромкий","a816", "тихий"},
                {"ruhig","тихий, спокойный","a817", "тихий, спокійний"},
                {"eng","узкий","a818","вузький"},
                {"breit","широкий","a819","широкий"},
                {"sauber","чистый","a820","чистий"},
                {"schmutzig","грязный","a821","брудний"},
                {"müde","усталый","a822","стомлений"},
                {"ehrlich","честный","a823","чесний"},
                {"bequem","уютный","a824", "затишний"},
                {"seltsam","странный","a825", "дивний"},
        };

        for (int i = 0; i < allCurrentLesson33.length; i++) {
            addLessonData(allCurrentLesson33[i][0],allCurrentLesson33[i][1],allCurrentLesson33[i][3],allCurrentLesson33[i][2],33);
        }


        String [][] allCurrentLesson34 = {
                {"eigen","собственный","a826","власний"},
                {"schmackhaft","вкусный","a827","смачний"},
                {"bitter","горький","a828","гіркий"},
                {"sauer","кислый","a829","кислий"},
                {"salzig","соленый","a830", "солоний"},
                {"bereit","готовый (что-то сделать)","a831", "готовий (щось зробити)"},
                {"fertig","готовый (завершенный)","a832", "готовий (завершений)"},
                {"aufmerksam","внимательный","a833","уважний"},
                {"vorsichtig","осторожный","a834","обережний"},
                {"Haupt...","главный","a835","головний"},
                {"fähig","способный","a836","здатний"},
                {"notwendig","необходимый","a837", "необхідний"},
                {"wichtig","важный","a838","важливий"},
                {"sicher","уверенный","a839", "упевнений"},
                {"echt","настоящий, истинный, правда","a840","справжній, істинний, правда"},
                {"besser","лучше; более хороший","a841","краще; бiльш кращий"},
                {"best","лучший","a842","кращий"},
                {"mehr","больше (по количеству); более","a843","більше (за кількістю); більше"},
                {"meist","наибольший (по количеству)","a844", "найбільший (за кількістю)"},
                {"lieber","лучше, охотней","a845","краще, охочіше"},
                {"am liebsten","охотней всего","a846", "найохочіше"},
                {"manchmal","иногда","a847","iноді"},
                {"nie","никогда","a848","ніколи"},
                {"selten","редко","a849","рідко"},
                {"gewöhnlich","обычно","a850","звичайно"},
        };

        for (int i = 0; i < allCurrentLesson34.length; i++) {
            addLessonData(allCurrentLesson34[i][0],allCurrentLesson34[i][1],allCurrentLesson34[i][3],allCurrentLesson34[i][2],34);
        }


        String [][] allCurrentLesson35 = {
                {"oft","часто","a851", "часто"},
                {"immer","всегда","a852", "завжди"},
                {"bald","скоро","a853","скоро"},
                {"vor kurzem","недавно","a854","нещодавно"},
                {"weit","далеко","a855","далеко"},
                {"genau","точно","a856","точно"},
                {"wahrscheinlich","вероятно","a857","ймовірно"},
                {"vielleicht","может быть","a858","може бути"},
                {"wirklich","действительно","a859","справді"},
                {"natürlich","конечно","a860","звісно"},
                {"sicherlich","наверняка, безусловно","a861","напевно, безумовно"},
                {"offensichtlich","очевидно","a862","очевидно"},
                {"besonders","особенно","a863","особливо"},
                {"gern","охотно","a864", "охоче"},
                {"ja", "да","a865", "так"},
                {"nein","нет","a866", "ні"},
                {"nicht","не","a867","не"},
                {"dieser","этот","a868", "цей"},
                {"jener","тот","a869","той"},
                {"viele","много (+ слово во множественном числе)","a870","багато (+ слово у множині)"},
                {"viel","много (+ слово в единственном числе)","a871","багато (+ слово в однині)"},
                {"wenige","мало (+ слово во множественном числе)","a872","мало (+ слово у множині)"},
                {"wenig","мало (+ слово в единственном числе)","a873","мало (+ слово в однині)"},
                {"einige","некоторые","a874","деякі"},
                {"mehrere","несколько","a875","декілька"},
        };

        for (int i = 0; i < allCurrentLesson35.length; i++) {
            addLessonData(allCurrentLesson35[i][0],allCurrentLesson35[i][1],allCurrentLesson35[i][3],allCurrentLesson35[i][2],35);
        }


        String [][] allCurrentLesson36 = {
                {"jemand","кто-то","a876","хтось"},
                {"niemand","никто","a877","ніхто"},
                {"etwas","что-то","a878", "щось"},
                {"nichts","ничто","a879","ніщо"},
                {"alles","всё","a880","усе"},
                {"alle","все","a881","всі"},
                {"hier","здесь","a882","тут"},
                {"hierher","сюда","a883","сюди"},
                {"dort","там","a884","там"},
                {"dorthin","туда","a885","туди"},
                {"da","тут; так как","a886","тут; так як"},
                {"jetzt","сейчас","a887","зараз"},
                {"wieder","снова, опять","a888","знову"},
                {"dann","потом","a889", "потім"},
                {"damals","тогда","a890","тоді"},
                {"als","чем (при сравнении); когда; в качестве","a891","чим (при порівнянні); коли; як"},
                {"bereits","уже","a892","вже"},
                {"nur","только","a893","тільки"},
                {"noch","ещё","a894","ще"},
                {"fast","почти","a895","майже"},
                {"sehr","очень","a896","дуже"},
                {"every","каждый","a897","кожен"},
                {"jeder","каждый","a898", "всім"},
                {"anderer","другой","a899","інший"},
                {"solcher","такой","a900","такий"},
        };

        for (int i = 0; i < allCurrentLesson36.length; i++) {
            addLessonData(allCurrentLesson36[i][0],allCurrentLesson36[i][1],allCurrentLesson36[i][3],allCurrentLesson36[i][2],36);
        }


        String [][] allCurrentLesson37 = {
                {"so","так","a901","так"},
                {"also","итак","a902", "отже"},
                {"oben","вверху","a903","вгорі"},
                {"unten","внизу","a904", "внизу"},
                {"zusammen","вместе","a905", "разом"},
                {"vorwärts","вперед","a906", "вперед"},
                {"vorne","впереди","a907", "попереду"},
                {"sogar","даже","a908","навіть"},
                {"genug","достаточно","a909", "достатньо"},
                {"auch","тоже, также","a910","теж, також"},
                {"und","и","a911", "і"},
                {"oder","или","a912","або"},
                {"aber","но","a913","але"},
                {"jedoch","однако","a914","однак"},
                {"weil","потому что, так как","a915","тому що, так як"},
                {"deshalb","поэтому","a916","тому"},
                {"ob","ли","a917", "якщо"},
                {"obwohl","хотя","a918","хоча"},
                {"dass","что","a919","що"},
                {"sein","быть, являться","a920","бути"},
                {"tun","делать (вообще)","a921","робити (взагалі)"},
                {"machen","делать (что-то)","a922","робити (щось)"},
                {"haben","иметь","a923","мати"},
                {"können","мочь (быть в состоянии)","a924", "бути здатним, могти"},
                {"dürfen","мочь (иметь разрешение)","a925", "дозволяти"},
        };

        for (int i = 0; i < allCurrentLesson37.length; i++) {
            addLessonData(allCurrentLesson37[i][0],allCurrentLesson37[i][1],allCurrentLesson37[i][3],allCurrentLesson37[i][2],37);
        }


        String [][] allCurrentLesson38 = {
                {"wollen","хотеть","a926","хотіти"},
                {"mögen","любить, нравиться","a927","любити, подобатися"},
                {"möchten","хотелось бы","a928","хотілося б"},
                {"müssen","быть должным (по внутренним причинам)","a929","повинен(з внутрішніх причин)"},
                {"sollen","быть должным (по внешним причинам)","a930","повинен (із зовнішніх причин)"},
                {"haben zu","должен (что-то сделать)","a931","повинен (щось зробити)"},
                {"sein zu","должен (быть сделанным)","a932","повинен (бути зробленим)"},
                {"brauchen","нуждаться","a933","потребувати"},
                {"gehen","идти","a934","йти"},
                {"fahren","ехать","a935","їхати"},
                {"finden","находить","a936","знаходити"},
                {"halten","держать; останавливаться","a937", "тримати; зупинятися"},
                {"stehen","стоять","a938","стояти"},
                {"sitzen","сидеть","a939","сидіти"},
                {"hören","слышать, слушать","a940", "чути, слухати"},
                {"gewinnen","побеждать, выигрывать","a941","перемагати, вигравати"},
                {"erhalten","получать (офиц.)","a942","отримувати (офіц.)"},
                {"bekommen","получать","a943","отримувати"},
                {"kriegen","получать (разг.)","a944","отримати"},
                {"kommen","приходить, приезжать","a945","приходити, приїжджати"},
                {"werden","становиться","a946","ставати"},
                {"laufen","бежать","a947", "бігти"},
                {"sehen","видеть","a948", "бачити"},
                {"schreiben","писать","a949","писати"},
                {"fallen","падать","a950","падати"},
        };

        for (int i = 0; i < allCurrentLesson38.length; i++) {
            addLessonData(allCurrentLesson38[i][0],allCurrentLesson38[i][1],allCurrentLesson38[i][3],allCurrentLesson38[i][2],38);
        }


        String [][] allCurrentLesson39 = {
                {"tragen","носить","a951","носити"},
                {"nehmen","брать","a952","брати"},
                {"geben","давать","a953","давати"},
                {"schenken","дарить","a954","дарувати"},
                {"entschuldigen","прощать","a955","прощати"},
                {"vergessen","забывать","a956","забувати"},
                {"essen","кушать","a957", "Їсти"},
                {"liegen","лежать","a958", "лежати, знаходитись"},
                {"legen","класть","a959", "покласти"},
                {"lügen","лгать","a960","брехати"},
                {"führen","вести","a961", "вести"},
                {"füttern","кормить","a962","годувати"},
                {"sagen","сказать","a963","сказати"},
                {"bezahlen","оплачивать","a964","оплачувати"},
                {"trinken","пить","a965","пити"},
                {"schwimmen","плавать","a966", "плавати"},
                {"singen","петь","a967","співати"},
                {"beginnen","начинать","a968", "починати"},
                {"klingen","звенеть","a969","дзвеніти"},
                {"rufen","звать; кричать","a970","кликати; кричати"},
                {"anrufen","звонить","a971","дзвонити"},
                {"sinken","опускаться; погружаться","a972","опускатися; занурюватися"},
                {"stinken","вонять","a973", "смердіти"},
                {"fliegen","летать","a974","літати"},
                {"wissen","знать","a975","знати"},
        };

        for (int i = 0; i < allCurrentLesson39.length; i++) {
            addLessonData(allCurrentLesson39[i][0],allCurrentLesson39[i][1],allCurrentLesson39[i][3],allCurrentLesson39[i][2],39);
        }


        String [][] allCurrentLesson40 = {
                {"kennen","знать, быть знакомым","a976","знати, бути знайомим"},
                {"zeichnen","рисовать, чертить","a977","малювати, креслити"},
                {"malen","рисовать","a978","малювати"},
                {"werfen","кидать, бросать","a979","кидати"},
                {"wachsen","расти","a980", "рости"},
                {"blasen","дуть","a981","дуть"},
                {"sprechen","разговаривать, говорить","a982", "говорити"},
                {"wählen","выбирать","a983","вибирати"},
                {"wecken","будить","a984","будити"},
                {"brechen","ломать","a985","ламати"},
                {"zeigen","показывать","a986", "показувати"},
                {"schneiden","резать","a987", "різати"},
                {"setzen, sich","садиться","a988","сідати"},
                {"kosten","стоить","a989","коштувати"},
                {"lassen","позволять; оставлять","a990","дозволяти; залишати"},
                {"schließen","закрывать","a991","закривати"},
                {"schlagen","ударять, бить","a992", "вдаряти, бити"},
                {"lesen","читать","a993","читати"},
                {"senden","посылать","a994","посилати"},
                {"verbringen","проводить (время)","a995","проводити (час)"},
                {"leihen","одолжить","a996","позичити"},
                {"bauen","строить","a997","будувати"},
                {"fühlen","чувствовать","a998", "відчувати"},
                {"treffen","встречать; попасть (в цель)","a999", "зустрічати; потрапити (в ціль)"},
                {"schlafen","спать","a1000","спати"},
        };

        for (int i = 0; i < allCurrentLesson40.length; i++) {
            addLessonData(allCurrentLesson40[i][0],allCurrentLesson40[i][1],allCurrentLesson40[i][3],allCurrentLesson40[i][2],40);
        }


        String [][] allCurrentLesson41 = {
                {"verlassen","покидать","a1001","покидати"},
                {"kaufen","покупать","a1002","купувати"},
                {"bringen","приносить, привозить","a1003","приносити, привозити"},
                {"lehren","учить, обучать","a1004", "вчити, навчати"},
                {"denken","думать","a1005","думати"},
                {"kämpfen","бороться","a1006","боротися"},
                {"fangen","ловить","a1007","ловити"},
                {"befürchten","бояться, опасаться","a1008", "боятися, побоюватися"},
                {"interessieren, sich","интересоваться","a1009","цікавитися"},
                {"überraschen","удивлять","a1010","дивувати"},
                {"aufstehen","вставать","a1011","вставати"},
                {"ziehen","тянуть; перемещаться","a1012","тягнути; переміщатися"},
                {"anziehen","надевать","a1013","одягати"},
                {"ausziehen","снимать (одежду)","a1014","знімати (одяг)"},
                {"leben","жить","a1015", "жити"},
                {"wohnen","проживать","a1016", "проживати"},
                {"arbeiten","работать","a1017","працювати"},
                {"fernsehen","смотреть телевизор","a1018","дивитися телевізор"},
                {"waschen","мыть, cтирать","a1019","мити, прати"},
                {"rasieren, sich","бриться","a1020","голитися"},
                {"versuchen","пытаться, пробовать","a1021", "намагатися, пробувати"},
                {"feiern","праздновать","a1022","святкувати"},
                {"lächeln","улыбаться","a1023","посміхатися"},
                {"lachen","смеяться","a1024","сміятися"},
                {"weinen","плакать","a1025", "плакати"},
        };

        for (int i = 0; i < allCurrentLesson41.length; i++) {
            addLessonData(allCurrentLesson41[i][0],allCurrentLesson41[i][1],allCurrentLesson41[i][3],allCurrentLesson41[i][2],41);
        }


        String [][] allCurrentLesson42 = {
                {"lernen","учить(ся)","a1026", "вчити(ся)"},
                {"studieren","учиться (в вузе); изучать","a1027","вчитися (у ВНЗ); вивчати"},
                {"ändern","(из)менять","a1028","(з)мінювати"},
                {"öffnen","открывать","a1029","відкривати"},
                {"tanzen","танцевать","a1030","танцювати"},
                {"fragen","спрашивать","a1031", "запитувати"},
                {"bitten","просить","a1032","просити"},
                {"bieten","предлагать","a1033","пропонувати"},
                {"antworten","отвечать","a1034","відповідати"},
                {"sammeln","собирать","a1035","збирати"},
                {"gefallen","нравиться","a1036","подобатися"},
                {"kochen","варить; готовить","a1037", "варити; готувати"},
                {"backen","печь","a1038","піч"},
                {"riechen","пахнуть; нюхать","a1039","пахнути; нюхати"},
                {"warten","ждать","a1040","чекати"},
                {"erwarten","ожидать","a1041", "очiкувати"},
                {"danken","благодарить","a1042","дякувати"},
                {"spielen","играть","a1043","грати"},
                {"rauchen","курить","a1044","палити"},
                {"wünschen","желать","a1045", "бажати"},
                {"schreien","кричать","a1046","кричати"},
                {"träumen","мечтать; видеть сон","a1047","мріяти; бачити сон"},
                {"hoffen","надеяться","a1048","сподіватися"},
                {"erinnern, sich","вспоминать","a1049","згадувати"},
                {"erinnern","напоминать","a1050","нагадувати"},
        };

        for (int i = 0; i < allCurrentLesson42.length; i++) {
            addLessonData(allCurrentLesson42[i][0],allCurrentLesson42[i][1],allCurrentLesson42[i][3],allCurrentLesson42[i][2],42);
        }


        String [][] allCurrentLesson43 = {
                {"genießen","наслаждаться; пользоваться","a1051","насолоджуватися; користуватися"},
                {"erklären","объяснять","a1052","пояснювати"},
                {"bleiben","оставаться","a1053","залишатися"},
                {"erholen, sich","отдыхать","a1054", "відпочивати"},
                {"abbiegen","повернуть","a1055","повернути"},
                {"heben","поднимать","a1056","піднімати"},
                {"glauben","верить, полагать","a1057", "вірити, думати"},
                {"helfen","помогать","a1058","допомагати"},
                {"bestellen","заказывать","a1059","замовляти"},
                {"befehlen","приказывать","a1060", "наказувати"},
                {"besuchen","посещать","a1061","відвідувати"},
                {"prüfen","проверять","a1062","перевіряти"},
                {"springen","прыгать","a1063", "стрибати"},
                {"reisen","путешествовать","a1064","подорожувати"},
                {"entscheiden","решать, принимать решение","a1065","вирішувати, приймати рішення"},
                {"lösen","решать (проблему); растворять","a1066", "вирішувати (проблему); розчиняти"},
                {"stimmen","быть правильным, верным","a1067","бути правильним, вірним"},
                {"zustimmen","соглашаться","a1068","погоджуватися"},
                {"retten","спасать","a1069","рятувати"},
                {"speichern","сохранять, накапливать","a1070", "зберігати, накопичувати"},
                {"sparen","экономить, копить","a1071","економити, збирати"},
                {"streiten","спорить","a1072","сперечатись"},
                {"zählen","считать","a1073","робити пiдрахунок"},
                {"sorgen, sich","беспокоиться","a1074","турбуватися"},
                {"scherzen","шутить","a1075","жартувати"},
        };

        for (int i = 0; i < allCurrentLesson43.length; i++) {
            addLessonData(allCurrentLesson43[i][0],allCurrentLesson43[i][1],allCurrentLesson43[i][3],allCurrentLesson43[i][2],43);
        }


        String [][] allCurrentLesson44 = {
                {"bewegen, sich","двигаться","a1076","рухатися"},
                {"passen","соответствовать, подходить","a1077","відповідати, підходити"},
                {"sterben","умирать","a1078","вмирати"},
                {"beeinflussen","влиять","a1079","впливати"},
                {"unterstützen","поддерживать","a1080","підтримувати"},
                {"beschreiben","описывать","a1081","описувати"},
                {"bestrafen","наказывать","a1082","карати"},
                {"vorhaben","намереваться","a1083","мати намір"},
                {"klagen","жаловаться","a1084","скаржитися"},
                {"vermeiden","избегать","a1085", "уникати"},
                {"zurückkehren","возвращаться","a1086","повертатися"},
                {"stören","беспокоить, мешать","a1087","турбувати, заважати"},
                {"vorstellen","представлять, знакомить","a1088","уявляти, знайомити"},
                {"kennenlernen","узнавать, знакомиться","a1089", "дізнаватись, знайомитися"},
                {"überzeugen","убеждать","a1090", "переконати"},
                {"genehmigen","позволять, санкционировать, одобрять","a1091","дозволяти, санкціонувати, схвалювати"},
                {"sie","они","a660","вони"},
        };

        for (int i = 0; i < allCurrentLesson44.length; i++) {
            addLessonData(allCurrentLesson44[i][0],allCurrentLesson44[i][1],allCurrentLesson44[i][3],allCurrentLesson44[i][2],44);
        }

    }


}