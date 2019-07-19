package com.toeii.pagingexample.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.toeii.pagingexample.ioThread

@Database(entities = [BookBean::class], version = 1,exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    companion object{

        val CHEESE_DATA = arrayListOf(
            "Abbaye de Belloc", "Abbaye du Mont des Cats", "Abertam", "Abondance", "Ackawi",
            "Acorn", "Adelost", "Affidelice au Chablis", "Afuega'l Pitu", "Airag",
            "Airedale", "Aisy Cendre", "Allgauer Emmentaler", "Alverca", "Ambert",  // 15
            "American Cheese", "Ami du Chambertin", "Anejo Enchilado", "Anneau du Vic-Bilh", "Anthoriro",
            "Appenzell", "Aragon", "Ardi Gasna", "Ardrahan", "Armenian String",
            "Aromes au Gene de Marc", "Asadero", "Asiago", "Aubisque Pyrenees", "Autun", // 30
            "Avaxtskyr", "Baby Swiss", "Babybel", "Baguette Laonnaise", "Bakers",
            "Baladi", "Balaton", "Bandal", "Banon", "Barry's Bay Cheddar", "Basing", "Basket Cheese", "Bath Cheese", "Bavarian Bergkase",
            "Baylough", "Beaufort", "Beauvoorde", "Beenleigh Blue", "Beer Cheese", "Bel Paese",
            "Bergader", "Bergere Bleue", "Berkswell", "Beyaz Peynir", "Bierkase", "Bishop Kennedy",
            "Blarney", "Bleu d'Auvergne", "Bleu de Gex", "Bleu de Laqueuille",
            "Bleu de Septmoncel", "Bleu Des Causses", "Blue", "Blue Castello", "Blue Rathgore",
            "Blue Vein (Australian)", "Blue Vein Cheeses", "Bocconcini", "Bocconcini (Australian)"
        )

        private var instance: AppDatabase? = null

        @Synchronized
        fun get(context: Context):AppDatabase{
            if(null == instance){
                instance = Room.databaseBuilder(context,AppDatabase::class.java,"db_book")
//                    .addCallback(object : RoomDatabase.Callback() {
//                        override fun onCreate(db: SupportSQLiteDatabase) {
//                            get(context).bookDao().insertAll(
//                                CHEESE_DATA.map {
//                                    BookBean(uid = 0,bookName = it,isLike = false)
//                                }
//                            )
//                        }
//                    })
                    .build()
            }
            return instance!!
        }


    }

    abstract fun bookDao(): BookDao



    //...Other Dao

}

