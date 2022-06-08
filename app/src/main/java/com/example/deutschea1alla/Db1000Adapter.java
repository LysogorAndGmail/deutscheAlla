package com.example.deutschea1alla;

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
        private static final String RU= "RU";    // Column III
        private static final String Sound= "Sound";    // Column III
        private static final String Lessons= "lesson";    // Column 4
        private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+
                " ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+ DE +" VARCHAR(255) ,"+ RU +" VARCHAR(225), "+ Sound +" VARCHAR(225), "+ Lessons +" INTEGER);";
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

    public long addLessonData(String de, String ru, String sound, int lesson)
    {
        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Db1000Adapter.myDbHelper1000.DE, de);
        contentValues.put(Db1000Adapter.myDbHelper1000.RU, ru);
        contentValues.put(Db1000Adapter.myDbHelper1000.Sound, sound);
        contentValues.put(Db1000Adapter.myDbHelper1000.Lessons, lesson);
        long id = dbb.insert(Db1000Adapter.myDbHelper1000.TABLE_NAME, null , contentValues);
        return id;
    }

    public ArrayList getDataArray() {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {Db1000Adapter.myDbHelper1000.UID, Db1000Adapter.myDbHelper1000.DE, Db1000Adapter.myDbHelper1000.RU, Db1000Adapter.myDbHelper1000.Sound, Db1000Adapter.myDbHelper1000.Lessons};
        Cursor cursor = db.query(Db1000Adapter.myDbHelper1000.TABLE_NAME, columns, null, null, null, null, null);
        StringBuffer buffer = new StringBuffer();

        String[][] allData;
        //ArrayList<CurrentNode> Currentnodes = null;
        Currentnodes = new ArrayList<Db1000Adapter.CurrentNode>();
        while (cursor.moveToNext()) {
            int uid = cursor.getInt(cursor.getColumnIndex(Db1000Adapter.myDbHelper1000.UID));
            String DE = cursor.getString(cursor.getColumnIndex(Db1000Adapter.myDbHelper1000.DE));
            String RU = cursor.getString(cursor.getColumnIndex(Db1000Adapter.myDbHelper1000.RU));
            String Sound = cursor.getString(cursor.getColumnIndex(Db1000Adapter.myDbHelper1000.Sound));
            int Lesson = cursor.getInt(cursor.getColumnIndex(Db1000Adapter.myDbHelper1000.Lessons));
            String[] onedate = {uid + "", DE, RU, Lesson + "", Sound};

            //Currentnodes = new ArrayList<CurrentNode>();
            Currentnodes.add(new Db1000Adapter.CurrentNode(onedate));
            //buffer.append(cid+ "   " + name + "   " + password +" " + lesson +" \n");
            //buffer.append("hi \n");

        }
        return Currentnodes;
    }

    public String[][] getDataArrayLesson(String Lesson) {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {Db1000Adapter.myDbHelper1000.UID, Db1000Adapter.myDbHelper1000.DE, Db1000Adapter.myDbHelper1000.RU, Db1000Adapter.myDbHelper1000.Sound, Db1000Adapter.myDbHelper1000.Lessons};
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
            String Sound = cursor.getString(cursor.getColumnIndex(Db1000Adapter.myDbHelper1000.Sound));
            int Lessons = cursor.getInt(cursor.getColumnIndex(Db1000Adapter.myDbHelper1000.Lessons));
            String[] onedate = {uid + "", DE, RU, Lessons + "", Sound};
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
        String[] columns = {Db1000Adapter.myDbHelper1000.UID, Db1000Adapter.myDbHelper1000.DE, Db1000Adapter.myDbHelper1000.RU, Db1000Adapter.myDbHelper1000.Sound, Db1000Adapter.myDbHelper1000.Lessons};
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
            String Sound = cursor.getString(cursor.getColumnIndex(Db1000Adapter.myDbHelper1000.Sound));
            int Lessons = cursor.getInt(cursor.getColumnIndex(Db1000Adapter.myDbHelper1000.Lessons));
            String[] onedate = {uid + "", DE, RU, Lessons + "", Sound};
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
            for (int i = 0; i < 5; i++) {
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
                {"Hallo!","Привет!","a1"},
                {"Guten Tag!","Добрый день!","a2"},
                {"Entschuldigung!","Извини(те)!","a3"},
                {"Bitte","пожалуйста","a4"},
                {"Danke","спасибо","a5"},
                {"Gern geschehen","не за что","a6"},
                {"Schade","жаль","a7"},
                {"Tschüss!","Пока!","a8"},
                {"Auf Wiedersehen!","До свидания!","a9"},
                {"die Leute","люди","a10"},
                {"der Mensch","человек","a11"},
                {"der Mann","мужчина; муж","a12"},
                {"die Frau","женщина; жена","a13"},
                {"das Kind","ребенок","a14"},
                {"der Junge","мальчик","a15"},
                {"das Mädchen","девочка","a16"},
                {"der Freund","друг","a17"},
                {"der Bekannte","знакомый","a18"},
                {"der Nachbar","сосед","a19"},
                {"der Gast","гость","a20"},
                {"der Chef","начальник; шеф","a21"},
                {"der Konkurrent","конкурент","a22"},
                {"der Kunde","клиент, покупатель","a23"},
                {"der Kollege","коллега","a24"},
                {"die Familie","семья","a25"},
        };

        for (int i = 0; i < allCurrentLesson1.length; i++) {
            addLessonData(allCurrentLesson1[i][0],allCurrentLesson1[i][1],allCurrentLesson1[i][2],1);
        }


        String [][] allCurrentLesson2 = {
                {"die Eltern","родители","a26"},
                {"der Vater","отец","a27"},
                {"der Vati","папа","a28"},
                {"die Mutter","мать","a29"},
                {"die Mutti","мама","a30"},
                {"der Sohn","сын","a31"},
                {"die Tochter","дочь","a32"},
                {"der Bruder","брат","a33"},
                {"die Schwester","сестра","a34"},
                {"der Großvater","дед","a35"},
                {"der Opa","дедушка","a36"},
                {"die Großmutter","бабушка","a37"},
                {"die Oma","бабуля","a38"},
                {"der Enkel","внук","a39"},
                {"die Enkelin","внучка","a40"},
                {"der Schwiegervater","тесть, свекор","a41"},
                {"die Schwiegermutter","тёща, свекровь","a42"},
                {"der Onkel","дядя","a43"},
                {"die Tante","тетя","a44"},
                {"der Cousin [кузэн]","двоюродный брат","a45"},
                {"die Kusine","двоюродная сестра","a46"},
                {"der Neffe","племянник","a47"},
                {"die Nichte","племянница","a48"},
                {"die Arbeit","работа","a49"},
                {"der Geschäftsmann","бизнесмен","a50"},
        };

        for (int i = 0; i < allCurrentLesson2.length; i++) {
            addLessonData(allCurrentLesson2[i][0],allCurrentLesson2[i][1],allCurrentLesson2[i][2],2);
        }


        String [][] allCurrentLesson3 = {
                {"der Lehrer","учитель","a51"},
                {"der Fahrer","водитель","a52"},
                {"der Arbeiter","рабочий","a53"},
                {"der Ingenieur","инженер","a54"},
                {"der Arzt","врач","a55"},
                {"der Rechtsanwalt","адвокат","a56"},
                {"der Journalist","журналист","a57"},
                {"der Krankenschwester","медсестра","a58"},
                {"der Verkäufer","продавец","a59"},
                {"der Kellner","официант","a60"},
                {"der Buchhalter","бухгалтер","a61"},
                {"der Maler","художник","a62"},
                {"der Musiker","музыкант","a63"},
                {"der Schauspieler","актер","a64"},
                {"der Student","студент","a65"},
                {"der Schüler","школьник, ученик","a66"},
                {"das Tier","животное","a67"},
                {"die Katze","кошка","a68"},
                {"der Hund","собака","a69"},
                {"der Vogel","птица","a70"},
                {"das Eichhörnchen","белка","a71"},
                {"der Wolf","волк","a72"},
                {"die Gans","гусь","a73"},
                {"die Giraffe","жираф","a74"},
                {"das Kaninchen","кролик","a75"},
        };

        for (int i = 0; i < allCurrentLesson3.length; i++) {
            addLessonData(allCurrentLesson3[i][0],allCurrentLesson3[i][1],allCurrentLesson3[i][2],3);
        }


        String [][] allCurrentLesson4 = {
                {"der Hase","заяц","a76"},
                {"die Kuh","корова","a77"},
                {"die Ratte","крыса","a78"},
                {"der Fuchs","лиса","a79"},
                {"das Pferd","лошадь","a80"},
                {"der Frosch","лягушка","a81"},
                {"der Bär","медведь","a82"},
                {"die Maus","мышь","a83"},
                {"der Affe","обезьяна","a84"},
                {"das Schwein","свинья","a85"},
                {"der Elefant","слон","a86"},
                {"die Ente","утка","a87"},
                {"das Land","страна; сельская местность; земля","a88"},
                {"Russland","Россия","a89"},
                {"Deutschland","Германия","a90"},
                {"Österreich","Австрия","a91"},
                {"die Schweiz","Швейцария","a92"},
                {"die Stadt","город","a93"},
                {"das Haus","дом","a94"},
                {"das Gebäude","здание","a95"},
                {"der Platz","площадь; место","a96"},
                {"der Eingang","вход","a97"},
                {"der Ausgang","выход","a98"},
                {"das Zentrum","центр","a99"},
                {"der Hof","двор","a100"},
        };

        for (int i = 0; i < allCurrentLesson4.length; i++) {
            addLessonData(allCurrentLesson4[i][0],allCurrentLesson4[i][1],allCurrentLesson4[i][2],4);
        }


        String [][] allCurrentLesson5 = {
                {"das Dach","крыша","a101"},
                {"der Zaun","забор","a102"},
                {"das Dorf","деревня, поселок","a103"},
                {"die Schule","школа","a104"},
                {"die Universität","университет","a105"},
                {"das Theater","театр","a106"},
                {"die Kirche","церковь","a107"},
                {"das Restaurant","ресторан","a108"},
                {"das Cafe","кафе","a109"},
                {"das Hotel","гостиница","a110"},
                {"die Bank","банк","a111"},
                {"das Kino","кинотеатр","a112"},
                {"das Krankenhaus","больница","a113"},
                {"die Polizei","полиция","a114"},
                {"das Postamt","почта","a115"},
                {"der Bahnhof","станция, вокзал","a116"},
                {"der Flughafen","аэропорт","a117"},
                {"das Geschäft","магазин","a118"},
                {"die Apotheke","аптека","a119"},
                {"der Markt","рынок","a120"},
                {"das Büro","офис","a121"},
                {"die Firma","фирма","a122"},
                {"der Betrieb","предприятие","a123"},
                {"die Straße","улица","a124"},
                {"der Weg","дорога","a125"},
        };

        for (int i = 0; i < allCurrentLesson5.length; i++) {
            addLessonData(allCurrentLesson5[i][0],allCurrentLesson5[i][1],allCurrentLesson5[i][2],5);
        }


        String [][] allCurrentLesson6 = {
                {"die Kreuzung","перекрёсток","a126"},
                {"die Haltestelle","остановка","a127"},
                {"der Gehsteig","тротуар","a128"},
                {"der Pfad","тропа, тропинка","a129"},
                {"der Garten","сад","a130"},
                {"der Park","парк","a131"},
                {"die Brücke","мост","a132"},
                {"der Fluss","река","a133"},
                {"der Wald","лес","a134"},
                {"das Feld","поле","a135"},
                {"der Berg","гора","a136"},
                {"der See","озеро","a137"},
                {"das Meer","море","a138"},
                {"der Ozean","океан","a139"},
                {"die Küste","морской берег, побережье","a140"},
                {"der Strand","пляж","a141"},
                {"der Sand","песок","a142"},
                {"die Insel","остров","a143"},
                {"die Grenze","граница","a144"},
                {"der Zoll","таможня","a145"},
                {"der Müll","мусор","a146"},
                {"die Abfälle","отходы","a147"},
                {"der Stein","камень","a148"},
                {"die Pflanze","растение","a149"},
                {"der Baum","дерево","a150"},
        };

        for (int i = 0; i < allCurrentLesson6.length; i++) {
            addLessonData(allCurrentLesson6[i][0],allCurrentLesson6[i][1],allCurrentLesson6[i][2],6);
        }


        String [][] allCurrentLesson7 = {
                {"das Gras","трава","a151"},
                {"die Blume","цветок","a152"},
                {"das Blatt","лист","a153"},
                {"die Wohnung","квартира","a154"},
                {"das Zimmer","комната","a155"},
                {"das Wohnzimmer","зал","a156"},
                {"das Schlafzimmer","спальня","a157"},
                {"das Badezimmer","ванная","a158"},
                {"die Dusche","душ","a159"},
                {"die Toilette","туалет","a160"},
                {"die Küche","кухня","a161"},
                {"der Flur","коридор","a162"},
                {"der Balkon","балкон","a163"},
                {"der Fußboden","пол","a164"},
                {"die Decke","потолок; одеяло","a165"},
                {"die Wand","стена","a166"},
                {"die Treppe","лестница","a167"},
                {"die Tür","дверь","a168"},
                {"das Fenster","окно","a169"},
                {"das Fensterbrett","подоконник","a170"},
                {"die Gardine","занавес(ка), штора","a171"},
                {"der Schalter","выключатель; окошко (кассы)","a172"},
                {"die Steckdose","розетка","a173"},
                {"der Wasserhahn","(водопроводный) кран","a174"},
                {"das Rohr","труба","a175"},
        };

        for (int i = 0; i < allCurrentLesson7.length; i++) {
            addLessonData(allCurrentLesson7[i][0],allCurrentLesson7[i][1],allCurrentLesson7[i][2],7);
        }


        String [][] allCurrentLesson8 = {
                {"der Schornstein","дымовая труба","a176"},
                {"die Möbel","мебель","a177"},
                {"der Tisch","стол","a178"},
                {"der Stuhl","стул","a179"},
                {"der Sessel","кресло","a180"},
                {"die Couch [кáуч]","диван","a181"},
                {"das Bett","кровать","a182"},
                {"der Schrank","шкаф","a183"},
                {"das Regal","полка","a184"},
                {"der Spiegel","зеркало","a185"},
                {"der Teppich","ковер","a186"},
                {"der Kühlschrank","холодильник","a187"},
                {"die Mikrowelle","микроволновка","a188"},
                {"der Ofen","печь, духовка","a189"},
                {"der Herd","кухонная плита","a190"},
                {"die Lebensmittel","еда, продукты","a191"},
                {"das Brot","хлеб","a192"},
                {"die Butter","сливочное масло","a193"},
                {"das Öl","растительное масло","a194"},
                {"der Käse","сыр","a195"},
                {"die Wurst","колбаса","a196"},
                {"das Würstchen","сосиска","a197"},
                {"der Schinken","ветчина","a198"},
                {"das Fleisch","мясо","a199"},
                {"das Rindfleisch","говядина","a200"},
        };

        for (int i = 0; i < allCurrentLesson8.length; i++) {
            addLessonData(allCurrentLesson8[i][0],allCurrentLesson8[i][1],allCurrentLesson8[i][2],8);
        }


        String [][] allCurrentLesson9 = {
                {"das Schweinefleisch","свинина","a201"},
                {"das Lammfleisch","баранина","a202"},
                {"das Hähnchen","курица (мясо)","a203"},
                {"das Kotelett","котлета, отбивная","a204"},
                {"der Fisch","рыба","a205"},
                {"das Ei","яйцо","a206"},
                {"der Salat","салат","a207"},
                {"die Pilze","грибы","a208"},
                {"der Mais","кукуруза","a209"},
                {"der Brei","каша","a210"},
                {"die Haferflocken","овсянка","a211"},
                {"die Suppe","суп","a212"},
                {"das belegte Brötchen","бутерброд","a213"},
                {"der Reis","рис","a214"},
                {"die Nudeln","лапша","a215"},
                {"das Mehl","мука","a216"},
                {"das Gewürz","специя, пряность","a217"},
                {"der Pfeffer","перец","a218"},
                {"das Salz","соль","a219"},
                {"die Zwiebel","лук (репчатый)","a220"},
                {"der Knoblauch","чеснок","a221"},
                {"die Soße","соус","a222"},
                {"das Gemüse","овощи","a223"},
                {"die Kartoffeln","картофель","a224"},
                {"die Mohrrübe","морковь","a225"},
        };

        for (int i = 0; i < allCurrentLesson9.length; i++) {
            addLessonData(allCurrentLesson9[i][0],allCurrentLesson9[i][1],allCurrentLesson9[i][2],9);
        }


        String [][] allCurrentLesson10 = {
                {"die Zuckerrübe","свекла","a226"},
                {"die Tomate","помидор","a227"},
                {"die Gurke","огурец","a228"},
                {"der Kohl","капуста","a229"},
                {"die Zucchini [цукини]","кабачок","a230"},
                {"die Aubergine [обэр'жинэ]","баклажан","a231"},
                {"die Bohnen","бобы","a232"},
                {"die Erbsen","горох","a233"},
                {"die Nuss","орех","a234"},
                {"das Obst","фрукты","a235"},
                {"der Apfel","яблоко","a236"},
                {"die Birne","груша","a237"},
                {"die Banane","банан","a238"},
                {"die Beere","ягода","a239"},
                {"die Erdbeere","клубника, земляника","a240"},
                {"die Himbeere","малина","a241"},
                {"die Kirsche","вишня","a242"},
                {"die Pflaume","слива","a243"},
                {"die Trauben","виноград","a244"},
                {"die Aprikose","абрикос","a245"},
                {"der Pfirsich","персик","a246"},
                {"die Melone","дыня","a247"},
                {"die Wassermelone","арбуз","a248"},
                {"der Kürbis","тыква","a249"},
                {"die Orange [орáнжэ]","апельсин","a250"},
        };

        for (int i = 0; i < allCurrentLesson10.length; i++) {
            addLessonData(allCurrentLesson10[i][0],allCurrentLesson10[i][1],allCurrentLesson10[i][2],10);
        }


        String [][] allCurrentLesson11 = {
                {"die Mandarine","мандарин","a251"},
                {"die Zitrone","лимон","a252"},
                {"die Ananas","ананас","a253"},
                {"der Zucker","сахар","a254"},
                {"der Honig","мёд","a255"},
                {"die Marmelade","варенье","a256"},
                {"der Kuchen","торт, пирог","a257"},
                {"das Brötchen","булочка","a258"},
                {"das Gebäck","печенье","a259"},
                {"die Süßigkeiten","конфеты, сладости","a260"},
                {"das Eis","мороженое; лёд","a261"},
                {"die Schokolade","шоколад","a262"},
                {"das Wasser","вода","a263"},
                {"das Sodawasser","газировка","a264"},
                {"der Saft","сок","a265"},
                {"der Wein","вино","a266"},
                {"der Tee","чай","a267"},
                {"der Kaffee","кофе","a268"},
                {"die Milch","молоко","a269"},
                {"die Sahne","сливки","a270"},
                {"der Joghurt","йогурт","a271"},
                {"der Quark","творог","a272"},
                {"das Geschirr","посуда","a273"},
                {"die Tasse","чашка","a274"},
                {"das Glas","стакан; стекло","a275"},
        };

        for (int i = 0; i < allCurrentLesson11.length; i++) {
            addLessonData(allCurrentLesson11[i][0],allCurrentLesson11[i][1],allCurrentLesson11[i][2],11);
        }


        String [][] allCurrentLesson12 = {
                {"der Becher","кружка","a276"},
                {"der Teller","тарелка","a277"},
                {"der Löffel","ложка","a278"},
                {"die Gabel","вилка","a279"},
                {"das Messer","нож","a280"},
                {"die Untertasse","блюдце","a281"},
                {"die Flasche","бутылка","a282"},
                {"die Serviette","салфетка","a283"},
                {"der Topf","кастрюля; горшок","a284"},
                {"die Pfanne","сковородка","a285"},
                {"der Kessel","чайник; котел","a286"},
                {"die Mahlzeit","принятие пищи, еда","a287"},
                {"das Frühstück","завтрак","a288"},
                {"das Mittagessen","обед","a289"},
                {"das Abendessen","ужин","a290"},
                {"der Transport","транспорт","a291"},
                {"das Flugzeug","самолет","a292"},
                {"das Auto","автомобиль","a293"},
                {"die Straßenbahn","трамвай","a294"},
                {"der Bus","автобус","a295"},
                {"der Zug","поезд","a296"},
                {"das Schiff","корабль","a297"},
                {"das Fahrrad","велосипед","a298"},
                {"die Zeit","время","a299"},
                {"die Minute","минута","a300"},
        };

        for (int i = 0; i < allCurrentLesson12.length; i++) {
            addLessonData(allCurrentLesson12[i][0],allCurrentLesson12[i][1],allCurrentLesson12[i][2],12);
        }


        String [][] allCurrentLesson13 = {
                {"die Stunde","час","a301"},
                {"die Woche","неделя","a302"},
                {"das Jahr","год","a303"},
                {"das Jahrhundert","век, столетие","a304"},
                {"das Mal","раз","a305"},
                {"vorgestern","позавчера","a306"},
                {"gestern","вчера","a307"},
                {"heute","сегодня","a308"},
                {"morgen","завтра","a309"},
                {"übermorgen","послезавтра","a310"},
                {"der Tag","день","a311"},
                {"der Morgen","утро","a312"},
                {"der Nachmittag","время до полудня","a313"},
                {"der Nachmittag","время после полудня","a314"},
                {"der Abend","вечер","a315"},
                {"die Nacht","ночь","a316"},
                {"der Montag","понедельник","a317"},
                {"der Dienstag","вторник","a318"},
                {"der Mittwoch","среда","a319"},
                {"der Donnerstag","четверг","a320"},
                {"der Freitag","пятница","a321"},
                {"der Samstag","суббота","a322"},
                {"der Sonntag","воскресенье","a323"},
                {"der Monat","месяц","a324"},
                {"der Januar","январь","a325"},
        };

        for (int i = 0; i < allCurrentLesson13.length; i++) {
            addLessonData(allCurrentLesson13[i][0],allCurrentLesson13[i][1],allCurrentLesson13[i][2],13);
        }


        String [][] allCurrentLesson14 = {
                {"der Februar","февраль","a326"},
                {"der März","март","a327"},
                {"der April","апрель","a328"},
                {"der Mai","май","a329"},
                {"der Juni","июнь","a330"},
                {"der Juli","июль","a331"},
                {"der August","август","a332"},
                {"der September","сентябрь","a333"},
                {"der Oktober","октябрь","a334"},
                {"der November","ноябрь","a335"},
                {"der Dezember","декабрь","a336"},
                {"die Jahreszeit","время года","a337"},
                {"der Frühling","весна","a338"},
                {"der Sommer","лето","a339"},
                {"der Herbst","осень","a340"},
                {"der Winter","зима","a341"},
                {"das Fest","праздник","a342"},
                {"das Weihnachten","Рождество","a343"},
                {"das Ostern","Пасха","a344"},
                {"der Geburtstag","день рождения","a345"},
                {"das Formular","формуляр, бланк","a346"},
                {"der Name","имя, фамилия; название;","a347"},
                {"der Vorname","имя","a348"},
                {"der Nachname","фамилия","a349"},
                {"der Mädchenname","девичья фамилия","a350"},
        };

        for (int i = 0; i < allCurrentLesson14.length; i++) {
            addLessonData(allCurrentLesson14[i][0],allCurrentLesson14[i][1],allCurrentLesson14[i][2],14);
        }


        String [][] allCurrentLesson15 = {
                {"der Geburtsdatum","дата рождения","a351"},
                {"der Geburtsort","место рождения","a352"},
                {"die Adresse","адрес","a353"},
                {"der Familienstand","семейное положение","a354"},
                {"ledig","холостой, незамужняя","a355"},
                {"verheiratet","женатый / замужняя","a356"},
                {"geschieden","разведенный","a357"},
                {"verwitwet","овдовевший","a358"},
                {"die Sache","вещь","a359"},
                {"der Kugelschreiber","ручка","a360"},
                {"der Bleistift","карандаш","a361"},
                {"das Buch","книга","a362"},
                {"das Heft","тетрадь","a363"},
                {"das Notizbuch","блокнот","a364"},
                {"die Notiz","пометка, запись","a365"},
                {"das Wörterbuch","словарь","a366"},
                {"der Buchstabe","буква","a367"},
                {"der Brief","письмо","a368"},
                {"der Umschlag","конверт","a369"},
                {"das Papier","бумага","a370"},
                {"die Zeitung","газета","a371"},
                {"die Zeitschrift","журнал","a372"},
                {"das Telefon","телефон","a373"},
                {"das Handy [хэнди]","сотовый телефон","a374"},
                {"die Uhr","часы","a375"},
        };

        for (int i = 0; i < allCurrentLesson15.length; i++) {
            addLessonData(allCurrentLesson15[i][0],allCurrentLesson15[i][1],allCurrentLesson15[i][2],15);
        }


        String [][] allCurrentLesson16 = {
                {"der Kamm","расчёска","a376"},
                {"der Fernseher","телевизор","a377"},
                {"das Bűgeleisen","утюг","a378"},
                {"die Seife","мыло","a379"},
                {"das Radio","радио","a380"},
                {"die Tasche","сумка","a381"},
                {"der Rucksack","рюкзак","a382"},
                {"die Karte","карта","a383"},
                {"die Postkarte","открытка","a384"},
                {"der Koffer","чемодан","a385"},
                {"das Geschenk","подарок","a386"},
                {"die Kamera","камера","a387"},
                {"der Fotoapparat","фотоаппарат","a388"},
                {"die Vase","ваза","a389"},
                {"das Taschentuch","носовой платок","a390"},
                {"der Ball","мяч","a391"},
                {"der Luftballon","воздушный шар(ик)","a392"},
                {"das Spielzeug","игрушка","a393"},
                {"die Fahrkarte","билет (на поезд)","a394"},
                {"der Fahrschein","билет (на городской транспорт)","a395"},
                {"das Ticket","билет (на самолёт)","a396"},
                {"das Gepäck","багаж","a397"},
                {"die Batterie","батарейка, аккумулятор","a398"},
                {"die Eimer","ведро","a399"},
                {"der Leine","веревка","a400"},
        };

        for (int i = 0; i < allCurrentLesson16.length; i++) {
            addLessonData(allCurrentLesson16[i][0],allCurrentLesson16[i][1],allCurrentLesson16[i][2],16);
        }


        String [][] allCurrentLesson17 = {
                {"die Tafel","доска (школьная)","a401"},
                {"das Brett","доска, брусок","a402"},
                {"der Kalender","календарь","a403"},
                {"der Laptop [лэптоп]","ноутбук","a404"},
                {"die Bürste","щетка","a405"},
                {"die Pinsel","кисть, кисточка","a406"},
                {"die Tastatur","клавиатура","a407"},
                {"die Taste","клавиша","a408"},
                {"der Schlüssel","ключ","a409"},
                {"das Rad","колесо","a410"},
                {"das Lenkrad","руль","a411"},
                {"der Kofferraum","багажник","a412"},
                {"das Benzin","бензин","a413"},
                {"die Geldbörse","кошелек","a414"},
                {"die Brieftasche","бумажник","a415"},
                {"die Lampe","лампа","a416"},
                {"das Lineal","линейка","a417"},
                {"die Schaufel","лопата","a418"},
                {"die Maschine","машина; механизм","a419"},
                {"der Hammer","молоток","a420"},
                {"die Schere","ножницы","a421"},
                {"die Brille","очки","a422"},
                {"das Paket","посылка","a423"},
                {"der Stock","палка","a424"},
                {"der Klebstoff","клей","a425"},
        };

        for (int i = 0; i < allCurrentLesson17.length; i++) {
            addLessonData(allCurrentLesson17[i][0],allCurrentLesson17[i][1],allCurrentLesson17[i][2],17);
        }


        String [][] allCurrentLesson18 = {
                {"das Handtuch","полотенце","a426"},
                {"der Draht","проволока","a427"},
                {"das Kabel","провод, кабель","a428"},
                {"die Seite","страница","a429"},
                {"die Taschenlampe","карманный фонарь","a430"},
                {"der Kasten","ящик","a431"},
                {"die Kiste","коробка","a432"},
                {"das Laken","простыня","a433"},
                {"das Kissen","подушка","a434"},
                {"die Kleidung","одежда","a435"},
                {"die Schuhe","обувь, туфли","a436"},
                {"die Stiefel","сапоги","a437"},
                {"die Turnschuhe","кроссовки","a438"},
                {"der Mantel","пальто","a439"},
                {"das Kleid","платье","a440"},
                {"der Anzug","костюм","a441"},
                {"das Hemd","рубашка","a442"},
                {"die Bluse","блузка","a443"},
                {"der Rock","юбка","a444"},
                {"die Handschuhe","перчатки","a445"},
                {"die Fäustlinge","варежки","a446"},
                {"der Hut","шляпа","a447"},
                {"die Mütze","шапка; кепка","a448"},
                {"die Jacke","Куртка","a449"},
                {"die Strickjacke","кофта","a450"},
        };

        for (int i = 0; i < allCurrentLesson18.length; i++) {
            addLessonData(allCurrentLesson18[i][0],allCurrentLesson18[i][1],allCurrentLesson18[i][2],18);
        }


        String [][] allCurrentLesson19 = {
                {"das Sakko","пиджак","a451"},
                {"der Schal","шарф","a452"},
                {"die Socke","носок","a453"},
                {"der Pullover","свитер","a454"},
                {"das T-Shirt [тишёрт]","футболка","a455"},
                {"die Kravatte","галстук","a456"},
                {"die Hose","брюки, штаны","a457"},
                {"die Shorts [шортс]","шорты","a458"},
                {"die Strumpfhose","колготки","a459"},
                {"die Strümpfe","чулки","a460"},
                {"die Jeans [джинз]","джинсы","a461"},
                {"die Kapuze","капюшон","a462"},
                {"der Gürtel","ремень","a463"},
                {"die Unterwäsche","нижнее белье","a464"},
                {"die Unterhose","трусы","a465"},
                {"der Büstenhaĺter","бюстгальтер","a466"},
                {"der Körper","тело","a467"},
                {"der Kopf","голова","a468"},
                {"das Gesicht","лицо","a469"},
                {"die Stirn","лоб","a470"},
                {"die Nase","нос","a471"},
                {"das Ohr","ухо","a472"},
                {"der Mund","рот","a473"},
                {"der Hals","горло; шея","a474"},
                {"das Auge","глаз","a475"},
        };

        for (int i = 0; i < allCurrentLesson19.length; i++) {
            addLessonData(allCurrentLesson19[i][0],allCurrentLesson19[i][1],allCurrentLesson19[i][2],19);
        }


        String [][] allCurrentLesson20 = {
                {"die Augenbraue","бровь","a476"},
                {"die Lippen","губы","a477"},
                {"der Zahn","зуб","a478"},
                {"das Haar","волос(ы)","a479"},
                {"der Schnurrbart","усы","a480"},
                {"der Bart","борода","a481"},
                {"die Wange","щека","a482"},
                {"das Kinn","подбородок","a483"},
                {"die Schulter","плечо","a484"},
                {"die Brust","грудь","a485"},
                {"das Herz","сердце","a486"},
                {"der Bauch","живот","a487"},
                {"der Rücken","спина","a488"},
                {"das Handgelenk","запястье","a489"},
                {"die Hand","рука, кисть (руки)","a490"},
                {"der Arm","рука (вся)","a491"},
                {"der Finger","палец (руки)","a492"},
                {"der Nagel","ноготь; гвоздь","a493"},
                {"der Ellenbogen","локоть","a494"},
                {"das Bein","нога","a495"},
                {"das Knie","колено","a496"},
                {"der Fuß","нога, ступня; подножие","a497"},
                {"die Ferse","пятка","a498"},
                {"die Zehe","палец (ноги)","a499"},
                {"der Knochen","кость","a500"},
        };

        for (int i = 0; i < allCurrentLesson20.length; i++) {
            addLessonData(allCurrentLesson20[i][0],allCurrentLesson20[i][1],allCurrentLesson20[i][2],20);
        }


        String [][] allCurrentLesson21 = {
                {"die Gesundheit","здоровье","a501"},
                {"gesund","здоровый","a502"},
                {"krank","больной","a503"},
                {"die Krankheit","болезнь","a504"},
                {"das Fieber","жар, (высокая) температура","a505"},
                {"der Husten","кашель","a506"},
                {"der Schnupfen","насморк","a507"},
                {"niesen","чихать","a508"},
                {"der Schmerz","боль","a509"},
                {"die Kopfschmerzen","головная боль","a510"},
                {"weh tun","болеть","a511"},
                {"die Grippe","грипп","a512"},
                {"die Erkältung","простуда","a513"},
                {"die Prellung","синяк, ушиб","a514"},
                {"das Ereignis","событие","a515"},
                {"die Geburt","рождение","a516"},
                {"das Spiel","игра","a517"},
                {"der Unterricht","занятие (урок)","a518"},
                {"der Urlaub","отпуск","a519"},
                {"die Ferien","каникулы","a520"},
                {"die Party [пати]","вечеринка","a521"},
                {"das Treffen","встреча","a522"},
                {"die Versammlung","собрание","a523"},
                {"die Hochzeit","свадьба","a524"},
                {"die Verhandlungen","переговоры","a525"},
        };

        for (int i = 0; i < allCurrentLesson21.length; i++) {
            addLessonData(allCurrentLesson21[i][0],allCurrentLesson21[i][1],allCurrentLesson21[i][2],21);
        }


        String [][] allCurrentLesson22 = {
                {"die Reise","поездка, путешествие","a526"},
                {"der Tod","смерть","a527"},
                {"das Wetter","погода","a528"},
                {"die Sonne","солнце","a529"},
                {"der Mond","луна","a530"},
                {"der Wind","ветер","a531"},
                {"der Nebel","туман","a532"},
                {"der Regen","дождь","a533"},
                {"der Schnee","снег","a534"},
                {"der Himmel","небо","a535"},
                {"die Wolke","облако","a536"},
                {"die Luft","воздух","a537"},
                {"die Temperatur","температура","a538"},
                {"der Grad","градус","a539"},
                {"die Kunst","искусство","a540"},
                {"die Musik","музыка","a541"},
                {"das Lied","песня","a542"},
                {"die Literatur","литература","a543"},
                {"die Geschichte","рассказ, история","a544"},
                {"der Film","фильм; пленка","a545"},
                {"die Skulptur","скульптура","a546"},
                {"das Bild","картина","a547"},
                {"das Foto","фото","a548"},
                {"die Werbung","реклама","a549"},
                {"der Einkäufe","покупки","a550"},
        };

        for (int i = 0; i < allCurrentLesson22.length; i++) {
            addLessonData(allCurrentLesson22[i][0],allCurrentLesson22[i][1],allCurrentLesson22[i][2],22);
        }


        String [][] allCurrentLesson23 = {
                {"die Größe","размер","a551"},
                {"der Preis","цена","a552"},
                {"das Geld","деньги","a553"},
                {"das Bargeld","наличные (деньги)","a554"},
                {"der Rabatt","скидка","a555"},
                {"das Konto","счет (банковский)","a556"},
                {"die Rechnung","счёт (для оплаты)","a557"},
                {"das Trinkgeld","чаевые","a558"},
                {"das Messen","измерение","a559"},
                {"die Entfernung","расстояние","a560"},
                {"die Distanz","дистанция","a561"},
                {"die Länge","длина","a562"},
                {"die Höhe","высота","a563"},
                {"die Tiefe","глубина","a564"},
                {"die Stärke","сила","a565"},
                {"die Geschwindigkeit","скорость","a566"},
                {"der Kilometer","километр","a567"},
                {"das Kilogramm","килограмм","a568"},
                {"das Pfund","фунт","a569"},
                {"das Gefühl","чувство","a570"},
                {"der Spaß","веселье","a571"},
                {"die Freude","радость","a572"},
                {"die Angst","страх","a573"},
                {"die Traurigkeit","печаль","a574"},
                {"die Leidenschaft","страсть, страстность","a575"},
        };

        for (int i = 0; i < allCurrentLesson23.length; i++) {
            addLessonData(allCurrentLesson23[i][0],allCurrentLesson23[i][1],allCurrentLesson23[i][2],23);
        }


        String [][] allCurrentLesson24 = {
                {"das Vergnügen","удовольствие","a576"},
                {"das Glück","счастье","a577"},
                {"der Frieden","мир (покой)","a578"},
                {"lieben","любить","a579"},
                {"hassen","ненавидеть","a580"},
                {"die Welt","мир (планета)","a581"},
                {"die Seele","душа","a582"},
                {"das Leben","жизнь","a583"},
                {"das Wissen","знание","a584"},
                {"die Aufgabe","задача, задание","a585"},
                {"die Übung","упражнение","a586"},
                {"das Problem","проблема","a587"},
                {"die Gelegenheit","(благоприятная) возможность","a588"},
                {"die Fähigkeit","способность","a589"},
                {"die Schönheit","красота","a590"},
                {"die Gefahr","опасность","a591"},
                {"die Erfahrung","опыт","a592"},
                {"das Gedächtnis","память","a593"},
                {"die Erinnerung","воспоминание","a594"},
                {"der Nutzen","польза","a595"},
                {"der Vorteil","преимущество","a596"},
                {"der Gewinn","выигрыш; прибыль, доход","a597"},
                {"die Gewohnheit","привычка","a598"},
                {"der Grund","причина; грунт, земля","a599"},
                {"die Konsequenz","(по)следствие","a600"},
        };

        for (int i = 0; i < allCurrentLesson24.length; i++) {
            addLessonData(allCurrentLesson24[i][0],allCurrentLesson24[i][1],allCurrentLesson24[i][2],24);
        }


        String [][] allCurrentLesson25 = {
                {"die Bedeutung","значение","a601"},
                {"das Mittel","средство","a602"},
                {"die Anstrengung","усилие","a603"},
                {"der Erfolg","успех","a604"},
                {"das Ziel","цель","a605"},
                {"das Wunder","чудо","a606"},
                {"die Wissenschaft","наука","a607"},
                {"die Sprache","язык","a608"},
                {"das Wort","слово","a609"},
                {"das Feuer","огонь","a610"},
                {"der Fall","случай; дело; падение","a611"},
                {"der Umstand","обстоятельство","a612"},
                {"der Gedanke","мысль","a613"},
                {"die Wahl","выбор","a614"},
                {"die Erlaubnis","разрешение","a615"},
                {"die Lieferung","доставка; поставка","a616"},
                {"die Leistung","достижение, успех","a617"},
                {"die Pflicht","обязанность","a618"},
                {"die Verzögerung","задержка, промедление","a619"},
                {"die Beziehung","отношение","a620"},
                {"die Note","оценка, отметка, знак; нота","a621"},
                {"der Fehler","ошибка","a622"},
                {"das Benehmen","поведение","a623"},
                {"die Einladung","приглашение","a624"},
                {"die Entwicklung","развитие","a625"},
        };

        for (int i = 0; i < allCurrentLesson25.length; i++) {
            addLessonData(allCurrentLesson25[i][0],allCurrentLesson25[i][1],allCurrentLesson25[i][2],25);
        }


        String [][] allCurrentLesson26 = {
                {"die Entscheidung","решение, принятие решения","a626"},
                {"die Lösung","решение (проблемы)","a627"},
                {"der Rat","совет","a628"},
                {"die Vereinbarung","соглашение; согласие","a629"},
                {"der Vertrag","договор","a630"},
                {"die Liste","список","a631"},
                {"der Streit","спор","a632"},
                {"der Test","тест, испытание","a633"},
                {"die Prüfung","экзамен","a634"},
                {"der Schritt","шаг","a635"},
                {"das Stück","кусок; штука","a636"},
                {"der Bereich","область, сфера","a637"},
                {"das Paar","пара","a638"},
                {"die Oberfläche","поверхность","a639"},
                {"die Ordnung","порядок","a640"},
                {"die Frage","вопрос","a641"},
                {"was","что","a642"},
                {"wer","кто","a643"},
                {"wo","где","a644"},
                {"wohin","куда","a645"},
                {"woher","откуда","a646"},
                {"wie","как","a647"},
                {"welcher","какой","a648"},
                {"warum","почему","a649"},
                {"wozu","зачем","a650"},
        };

        for (int i = 0; i < allCurrentLesson26.length; i++) {
            addLessonData(allCurrentLesson26[i][0],allCurrentLesson26[i][1],allCurrentLesson26[i][2],26);
        }


        String [][] allCurrentLesson27 = {
                {"wenn","когда; если","a651"},
                {"wie viel (wie viele)","сколько","a652"},
                {"ich","я","a653"},
                {"du","т","a654"},
                {"er","он","a655"},
                {"sie","она","a656"},
                {"es","оно","a657"},
                {"wir","мы","a658"},
                {"ihr","вы","a659"},
                {"sie","они","a660"},
                {"Sie","Вы","a661"},
                {"mein","мой","a662"},
                {"dein","твой","a663"},
                {"sein","его","a664"},
                {"ihr","её; их","a665"},
                {"unser","наш","a666"},
                {"euer","ваш","a667"},
                {"Ihr","Ваш","a668"},
                {"aus","из","a669"},
                {"von","от; передает род. падеж; с (такого-то времени)","a670"},
                {"seit","с (такого-то времени и поныне)","a671"},
                {"zu","к; слишком","a672"},
                {"in","в; через (такое-то время)","a673"},
                {"auf","на","a674"},
                {"unter","под","a675"},
        };

        for (int i = 0; i < allCurrentLesson27.length; i++) {
            addLessonData(allCurrentLesson27[i][0],allCurrentLesson27[i][1],allCurrentLesson27[i][2],27);
        }


        String [][] allCurrentLesson28 = {
                {"hinter","позади","a676"},
                {"mit","с","a677"},
                {"ohne","без","a678"},
                {"vor","перед, до; (столько-то времени) назад","a679"},
                {"nach","после; в (такой-то город или страну)","a680"},
                {"zwischen","между","a681"},
                {"neben","возле, около","a682"},
                {"für","для; на (такое-то время)","a683"},
                {"während","во время","a684"},
                {"um","вокруг","a685"},
                {"wegen","из-за","a686"},
                {"über","над; о; свыше","a687"},
                {"gegen","против","a688"},
                {"unter","под; среди","a689"},
                {"durch","через","a690"},
                {"pro","(километров) в (час)","a691"},
                {"die Zahl","число, цифра; количество","a692"},
                {"null","ноль","a693"},
                {"eins","один","a694"},
                {"zwei","два","a695"},
                {"drei","три","a696"},
                {"vier","четыре","a697"},
                {"fünf","пять","a698"},
                {"sechs","шесть","a699"},
                {"sieben","семь","a700"},
        };

        for (int i = 0; i < allCurrentLesson28.length; i++) {
            addLessonData(allCurrentLesson28[i][0],allCurrentLesson28[i][1],allCurrentLesson28[i][2],28);
        }


        String [][] allCurrentLesson29 = {
                {"acht","восемь","a701"},
                {"neun","девять","a702"},
                {"zehn","десять","a703"},
                {"elf","одиннадцать","a704"},
                {"zwölf","двенадцать","a705"},
                {"dreizehn","тринадцать","a706"},
                {"vierzehn","четырнадцать","a707"},
                {"fünfzehn","пятнадцать","a708"},
                {"sechzehn","шестнадцать","a709"},
                {"siebzehn","семнадцать","a710"},
                {"achtzehn","восемнадцать","a711"},
                {"neunzehn","девятнадцать","a712"},
                {"zwanzig","двадцать","a713"},
                {"dreißig","тридцать","a714"},
                {"vierzig","сорок","a715"},
                {"fünfzig","пятьдесят","a716"},
                {"sechzig","шестьдесят","a717"},
                {"siebzig","семьдесят","a718"},
                {"achtzig","восемьдесят","a719"},
                {"neunzig","девяносто","a720"},
                {"hundert","сто","a721"},
                {"tausend","тысяча","a722"},
                {"der erste","первый","a723"},
                {"der zweite","второй","a724"},
                {"der dritte","третий","a725"},
        };

        for (int i = 0; i < allCurrentLesson29.length; i++) {
            addLessonData(allCurrentLesson29[i][0],allCurrentLesson29[i][1],allCurrentLesson29[i][2],29);
        }


        String [][] allCurrentLesson30 = {
                {"der vierte","четвертый","a726"},
                {"die Farbe","цвет, краска","a727"},
                {"schwarz","чёрный","a728"},
                {"blau","голубой; синий","a729"},
                {"braun","коричневый","a730"},
                {"grün","зелёный","a731"},
                {"grau","серый","a732"},
                {"rot","красный","a733"},
                {"weiß","белый","a734"},
                {"gelb","желтый","a735"},
                {"rosa","розовый","a736"},
                {"violett","фиолетовый","a737"},
                {"alt","старый","a738"},
                {"jung","молодой","a739"},
                {"neu","новый","a740"},
                {"groß","большой","a741"},
                {"riesig","огромный","a742"},
                {"klein","маленький","a743"},
                {"dick","толстый; густой","a744"},
                {"dünn","тонкий","a745"},
                {"hungrig","голодный","a746"},
                {"satt","сытый","a747"},
                {"voll","полный","a748"},
                {"leer","пустой","a749"},
                {"gut","хороший; добрый","a750"},
        };

        for (int i = 0; i < allCurrentLesson30.length; i++) {
            addLessonData(allCurrentLesson30[i][0],allCurrentLesson30[i][1],allCurrentLesson30[i][2],30);
        }


        String [][] allCurrentLesson31 = {
                {"ausgezeichnet","отличный","a751"},
                {"erstaunlich","удивительный","a752"},
                {"wunderschön","прекрасный, чудесный","a753"},
                {"schlecht","плохой","a754"},
                {"schrecklich","ужасный","a755"},
                {"früh","ранний; рано","a756"},
                {"spät","поздний; поздно","a757"},
                {"vorig","последний, прошлый","a758"},
                {"nächst","следующий","a759"},
                {"frei","свободный","a760"},
                {"kostenlos","бесплатный","a761"},
                {"beschäftigt","занятый","a762"},
                {"heiß","жаркий; горячий","a763"},
                {"warm","тёплый","a764"},
                {"kalt","холодный","a765"},
                {"kühl","прохладный","a766"},
                {"scharf","острый","a767"},
                {"stumpf","тупой (нож)","a768"},
                {"dumm","глупый","a769"},
                {"klug","умный","a770"},
                {"schön","красивый","a771"},
                {"attraktiv","привлекательный","a772"},
                {"hübsch","симпатичный","a773"},
                {"hässlich","уродливый, безобразный, отвратительный","a774"},
                {"hoch","высокий","a775"},
        };

        for (int i = 0; i < allCurrentLesson31.length; i++) {
            addLessonData(allCurrentLesson31[i][0],allCurrentLesson31[i][1],allCurrentLesson31[i][2],31);
        }


        String [][] allCurrentLesson32 = {
                {"niedrig","низкий","a776"},
                {"lang","длинный; долгий","a777"},
                {"kurz","короткий","a778"},
                {"schwer","тяжёлый","a779"},
                {"leicht","лёгкий","a780"},
                {"schwierig","трудный, тяжелый","a781"},
                {"einfach","простой","a782"},
                {"dunkel","тёмный","a783"},
                {"hell","светлый","a784"},
                {"teuer","дорогой (о цене)","a785"},
                {"lieb","дорогой, милый","a786"},
                {"billig","дешёвый","a787"},
                {"arm","бедный","a788"},
                {"reich","богатый","a789"},
                {"gerade","прямой; прямо","a790"},
                {"links","cлева","a791"},
                {"rechts","справа","a792"},
                {"falsch","неправильный","a793"},
                {"schnell","быстрый","a794"},
                {"langsam","медленный","a795"},
                {"weich","мягкий","a796"},
                {"hart","твёрдый; жесткий; трудный","a797"},
                {"traurig","печальный","a798"},
                {"froh","радостный","a799"},
                {"glücklich","счастливый","a800"},
        };

        for (int i = 0; i < allCurrentLesson32.length; i++) {
            addLessonData(allCurrentLesson32[i][0],allCurrentLesson32[i][1],allCurrentLesson32[i][2],32);
        }


        String [][] allCurrentLesson33 = {
                {"lustig","веселый","a801"},
                {"böse","сердитый, злой","a802"},
                {"höflich","вежливый","a803"},
                {"grob","грубый","a804"},
                {"zart","нежный","a805"},
                {"mutig","смелый","a806"},
                {"feige","трусливый","a807"},
                {"scheu","застенчивый","a808"},
                {"fleißig","прилежный","a809"},
                {"faul","ленивый","a810"},
                {"nützlich","полезный","a811"},
                {"nutzlos","бесполезный","a812"},
                {"stark","сильный; крепкий","a813"},
                {"schwach","слабый","a814"},
                {"laut","громкий, шумный","a815"},
                {"leise","тихий, негромкий","a816"},
                {"ruhig","тихий, спокойный","a817"},
                {"eng","узкий","a818"},
                {"breit","широкий","a819"},
                {"sauber","чистый","a820"},
                {"schmutzig","грязный","a821"},
                {"müde","усталый","a822"},
                {"ehrlich","честный","a823"},
                {"bequem","уютный","a824"},
                {"seltsam","странный","a825"},
        };

        for (int i = 0; i < allCurrentLesson33.length; i++) {
            addLessonData(allCurrentLesson33[i][0],allCurrentLesson33[i][1],allCurrentLesson33[i][2],33);
        }


        String [][] allCurrentLesson34 = {
                {"eigen","собственный","a826"},
                {"schmackhaft","вкусный","a827"},
                {"bitter","горький","a828"},
                {"sauer","кислый","a829"},
                {"salzig","соленый","a830"},
                {"bereit","готовый (что-то сделать)","a831"},
                {"fertig","готовый (завершенный)","a832"},
                {"aufmerksam","внимательный","a833"},
                {"vorsichtig","осторожный","a834"},
                {"Haupt...","главный","a835"},
                {"fähig","способный","a836"},
                {"notwendig","необходимый","a837"},
                {"wichtig","важный","a838"},
                {"sicher","уверенный","a839"},
                {"echt","настоящий, истинный, правда","a840"},
                {"besser","лучше; более хороший","a841"},
                {"best","лучший","a842"},
                {"mehr","больше (по количеству); более","a843"},
                {"meist","наибольший (по количеству)","a844"},
                {"lieber","лучше, охотней","a845"},
                {"am liebsten","охотней всего","a846"},
                {"manchmal","иногда","a847"},
                {"nie","никогда","a848"},
                {"selten","редко","a849"},
                {"gewöhnlich","обычно","a850"},
        };

        for (int i = 0; i < allCurrentLesson34.length; i++) {
            addLessonData(allCurrentLesson34[i][0],allCurrentLesson34[i][1],allCurrentLesson34[i][2],34);
        }


        String [][] allCurrentLesson35 = {
                {"oft","часто","a851"},
                {"immer","всегда","a852"},
                {"bald","скоро","a853"},
                {"vor kurzem","недавно","a854"},
                {"weit","далеко","a855"},
                {"genau","точно","a856"},
                {"wahrscheinlich","вероятно","a857"},
                {"vielleicht","может быть","a858"},
                {"wirklich","действительно","a859"},
                {"natürlich","конечно","a860"},
                {"sicherlich","наверняка, безусловно","a861"},
                {"offensichtlich","очевидно","a862"},
                {"besonders","особенно","a863"},
                {"gern","охотно","a864"},
                {"ja","да","a865"},
                {"nein","нет","a866"},
                {"nicht","не","a867"},
                {"dieser","этот","a868"},
                {"jener","тот","a869"},
                {"viele","много (+ слово во множественном числе)","a870"},
                {"viel","много (+ слово в единственном числе)","a871"},
                {"wenige","мало (+ слово во множественном числе)","a872"},
                {"wenig","мало (+ слово в единственном числе)","a873"},
                {"einige","некоторые","a874"},
                {"mehrere","несколько","a875"},
        };

        for (int i = 0; i < allCurrentLesson35.length; i++) {
            addLessonData(allCurrentLesson35[i][0],allCurrentLesson35[i][1],allCurrentLesson35[i][2],35);
        }


        String [][] allCurrentLesson36 = {
                {"jemand","кто-то","a876"},
                {"niemand","никто","a877"},
                {"etwas","что-то","a878"},
                {"nichts","ничто","a879"},
                {"alles","всё","a880"},
                {"alle","все","a881"},
                {"hier","здесь","a882"},
                {"hierher","сюда","a883"},
                {"dort","там","a884"},
                {"dorthin","туда","a885"},
                {"da","тут; так как","a886"},
                {"jetzt","сейчас","a887"},
                {"wieder","снова, опять","a888"},
                {"dann","потом","a889"},
                {"damals","тогда","a890"},
                {"als","чем (при сравнении); когда; в качестве","a891"},
                {"bereits","уже","a892"},
                {"nur","только","a893"},
                {"noch","ещё","a894"},
                {"fast","почти","a895"},
                {"sehr","очень","a896"},
                {"every","каждый","a897"},
                {"jeder","каждый","a898"},
                {"anderer","другой","a899"},
                {"solcher","такой","a900"},
        };

        for (int i = 0; i < allCurrentLesson36.length; i++) {
            addLessonData(allCurrentLesson36[i][0],allCurrentLesson36[i][1],allCurrentLesson36[i][2],36);
        }


        String [][] allCurrentLesson37 = {
                {"so","так","a901"},
                {"also","итак","a902"},
                {"oben","вверху","a903"},
                {"unten","внизу","a904"},
                {"zusammen","вместе","a905"},
                {"vorwärts","вперед","a906"},
                {"vorne","впереди","a907"},
                {"sogar","даже","a908"},
                {"genug","достаточно","a909"},
                {"auch","тоже, также","a910"},
                {"und","и","a911"},
                {"oder","или","a912"},
                {"aber","но","a913"},
                {"jedoch","однако","a914"},
                {"weil","потому что, так как","a915"},
                {"deshalb","поэтому","a916"},
                {"ob","ли","a917"},
                {"obwohl","хотя","a918"},
                {"dass","что","a919"},
                {"sein","быть, являться","a920"},
                {"tun","делать (вообще)","a921"},
                {"machen","делать (что-то)","a922"},
                {"haben","иметь","a923"},
                {"können","мочь (быть в состоянии)","a924"},
                {"dürfen","мочь (иметь разрешение)","a925"},
        };

        for (int i = 0; i < allCurrentLesson37.length; i++) {
            addLessonData(allCurrentLesson37[i][0],allCurrentLesson37[i][1],allCurrentLesson37[i][2],37);
        }


        String [][] allCurrentLesson38 = {
                {"wollen","хотеть","a926"},
                {"mögen","любить, нравиться","a927"},
                {"möchten","хотелось бы","a928"},
                {"müssen","быть должным (по внутренним причинам)","a929"},
                {"sollen","быть должным (по внешним причинам)","a930"},
                {"haben zu","должен (что-то сделать)","a931"},
                {"sein zu","должен (быть сделанным)","a932"},
                {"brauchen","нуждаться","a933"},
                {"gehen","идти","a934"},
                {"fahren","ехать","a935"},
                {"finden","находить","a936"},
                {"halten","держать; останавливаться","a937"},
                {"stehen","стоять","a938"},
                {"sitzen","сидеть","a939"},
                {"hören","слышать, слушать","a940"},
                {"gewinnen","побеждать, выигрывать","a941"},
                {"erhalten","получать (офиц.)","a942"},
                {"bekommen","получать","a943"},
                {"kriegen","получать (разг.)","a944"},
                {"kommen","приходить, приезжать","a945"},
                {"werden","становиться; передаёт будущее время; передаёт страдательный залог","a946"},
                {"laufen","бежать","a947"},
                {"sehen","видеть","a948"},
                {"schreiben","писать","a949"},
                {"fallen","падать","a950"},
        };

        for (int i = 0; i < allCurrentLesson38.length; i++) {
            addLessonData(allCurrentLesson38[i][0],allCurrentLesson38[i][1],allCurrentLesson38[i][2],38);
        }


        String [][] allCurrentLesson39 = {
                {"tragen","носить","a951"},
                {"nehmen","брать","a952"},
                {"geben","давать","a953"},
                {"schenken","дарить","a954"},
                {"entschuldigen","прощать","a955"},
                {"vergessen","забывать","a956"},
                {"essen","кушать","a957"},
                {"liegen","лежать","a958"},
                {"legen","класть","a959"},
                {"lügen","лгать","a960"},
                {"führen","вести","a961"},
                {"füttern","кормить","a962"},
                {"sagen","сказать","a963"},
                {"bezahlen","оплачивать","a964"},
                {"trinken","пить","a965"},
                {"schwimmen","плавать","a966"},
                {"singen","петь","a967"},
                {"beginnen","начинать","a968"},
                {"klingen","звенеть","a969"},
                {"rufen","звать; кричать","a970"},
                {"anrufen","звонить","a971"},
                {"sinken","опускаться; погружаться","a972"},
                {"stinken","вонять","a973"},
                {"fliegen","летать","a974"},
                {"wissen","знать","a975"},
        };

        for (int i = 0; i < allCurrentLesson39.length; i++) {
            addLessonData(allCurrentLesson39[i][0],allCurrentLesson39[i][1],allCurrentLesson39[i][2],39);
        }


        String [][] allCurrentLesson40 = {
                {"kennen","знать, быть знакомым","a976"},
                {"zeichnen","рисовать, чертить","a977"},
                {"malen","рисовать","a978"},
                {"werfen","кидать, бросать","a979"},
                {"wachsen","расти","a980"},
                {"blasen","дуть","a981"},
                {"sprechen","разговаривать, говорить","a982"},
                {"wählen","выбирать","a983"},
                {"wecken","будить","a984"},
                {"brechen","ломать","a985"},
                {"zeigen","показывать","a986"},
                {"schneiden","резать","a987"},
                {"setzen, sich","садиться","a988"},
                {"kosten","стоить","a989"},
                {"lassen","позволять; оставлять","a990"},
                {"schließen","закрывать","a991"},
                {"schlagen","ударять, бить","a992"},
                {"lesen","читать","a993"},
                {"senden","посылать","a994"},
                {"verbringen","проводить (время)","a995"},
                {"leihen","одолжить","a996"},
                {"bauen","строить","a997"},
                {"fühlen","чувствовать","a998"},
                {"treffen","встречать; попасть (в цель)","a999"},
                {"schlafen","спать","a1000"},
        };

        for (int i = 0; i < allCurrentLesson40.length; i++) {
            addLessonData(allCurrentLesson40[i][0],allCurrentLesson40[i][1],allCurrentLesson40[i][2],40);
        }


        String [][] allCurrentLesson41 = {
                {"verlassen","покидать","a1001"},
                {"kaufen","покупать","a1002"},
                {"bringen","приносить, привозить","a1003"},
                {"lehren","учить, обучать","a1004"},
                {"denken","думать","a1005"},
                {"kämpfen","бороться","a1006"},
                {"fangen","ловить","a1007"},
                {"befürchten","бояться, опасаться","a1008"},
                {"interessieren, sich","интересоваться","a1009"},
                {"überraschen","удивлять","a1010"},
                {"aufstehen","вставать","a1011"},
                {"ziehen","тянуть; перемещаться","a1012"},
                {"anziehen","надевать","a1013"},
                {"ausziehen","снимать (одежду)","a1014"},
                {"leben","жить","a1015"},
                {"wohnen","проживать","a1016"},
                {"arbeiten","работать","a1017"},
                {"fernsehen","смотреть телевизор","a1018"},
                {"waschen","мыть, cтирать","a1019"},
                {"rasieren, sich","бриться","a1020"},
                {"versuchen","пытаться, пробовать","a1021"},
                {"feiern","праздновать","a1022"},
                {"lächeln","улыбаться","a1023"},
                {"lachen","смеяться","a1024"},
                {"weinen","плакать","a1025"},
        };

        for (int i = 0; i < allCurrentLesson41.length; i++) {
            addLessonData(allCurrentLesson41[i][0],allCurrentLesson41[i][1],allCurrentLesson41[i][2],41);
        }


        String [][] allCurrentLesson42 = {
                {"lernen","учить(ся)","a1026"},
                {"studieren","учиться (в вузе); изучать","a1027"},
                {"ändern","(из)менять","a1028"},
                {"öffnen","открывать","a1029"},
                {"tanzen","танцевать","a1030"},
                {"fragen","спрашивать","a1031"},
                {"bitten","просить","a1032"},
                {"bieten","предлагать","a1033"},
                {"antworten","отвечать","a1034"},
                {"sammeln","собирать","a1035"},
                {"gefallen","нравиться","a1036"},
                {"kochen","варить; готовить","a1037"},
                {"backen","печь","a1038"},
                {"riechen","пахнуть; нюхать","a1039"},
                {"warten","ждать","a1040"},
                {"erwarten","ожидать","a1041"},
                {"danken","благодарить","a1042"},
                {"spielen","играть","a1043"},
                {"rauchen","курить","a1044"},
                {"wünschen","желать","a1045"},
                {"schreien","кричать","a1046"},
                {"träumen","мечтать; видеть сон","a1047"},
                {"hoffen","надеяться","a1048"},
                {"erinnern, sich","вспоминать","a1049"},
                {"erinnern","напоминать","a1050"},
        };

        for (int i = 0; i < allCurrentLesson42.length; i++) {
            addLessonData(allCurrentLesson42[i][0],allCurrentLesson42[i][1],allCurrentLesson42[i][2],42);
        }


        String [][] allCurrentLesson43 = {
                {"genießen","наслаждаться; пользоваться","a1051"},
                {"erklären","объяснять","a1052"},
                {"bleiben","оставаться","a1053"},
                {"erholen, sich","отдыхать","a1054"},
                {"abbiegen","повернуть","a1055"},
                {"heben","поднимать","a1056"},
                {"glauben","верить, полагать","a1057"},
                {"helfen","помогать","a1058"},
                {"bestellen","заказывать","a1059"},
                {"befehlen","приказывать","a1060"},
                {"besuchen","посещать","a1061"},
                {"prüfen","проверять","a1062"},
                {"springen","прыгать","a1063"},
                {"reisen","путешествовать","a1064"},
                {"entscheiden","решать, принимать решение","a1065"},
                {"lösen","решать (проблему); растворять","a1066"},
                {"stimmen","быть правильным, верным","a1067"},
                {"zustimmen","соглашаться","a1068"},
                {"retten","спасать","a1069"},
                {"speichern","сохранять, накапливать","a1070"},
                {"sparen","экономить, копить","a1071"},
                {"streiten","спорить","a1072"},
                {"zählen","считать","a1073"},
                {"sorgen, sich","беспокоиться","a1074"},
                {"scherzen","шутить","a1075"},
        };

        for (int i = 0; i < allCurrentLesson43.length; i++) {
            addLessonData(allCurrentLesson43[i][0],allCurrentLesson43[i][1],allCurrentLesson43[i][2],43);
        }


        String [][] allCurrentLesson44 = {
                {"bewegen, sich","двигаться","a1076"},
                {"passen","соответствовать, подходить","a1077"},
                {"sterben","умирать","a1078"},
                {"beeinflussen","влиять","a1079"},
                {"unterstützen","поддерживать","a1080"},
                {"beschreiben","описывать","a1081"},
                {"bestrafen","наказывать","a1082"},
                {"vorhaben","намереваться","a1083"},
                {"klagen","жаловаться","a1084"},
                {"vermeiden","избегать","a1085"},
                {"zurückkehren","возвращаться","a1086"},
                {"stören","беспокоить, мешать","a1087"},
                {"vorstellen","представлять, знакомить","a1088"},
                {"kennenlernen","узнавать, знакомиться","a1089"},
                {"überzeugen","убеждать","a1090"},
                {"genehmigen","позволять, санкционировать, одобрять","a1091"},
                {"schätzen","ценить","a1092"},
        };

        for (int i = 0; i < allCurrentLesson44.length; i++) {
            addLessonData(allCurrentLesson44[i][0],allCurrentLesson44[i][1],allCurrentLesson44[i][2],44);
        }

    }


}