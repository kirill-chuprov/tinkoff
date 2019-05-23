package com.tinkoff.task.repository.data.local.db;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import java.lang.IllegalStateException;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class TinkoffDb_Impl extends TinkoffDb {
  private volatile DepositePointDao _depositePointDao;

  private volatile PartnerDao _partnerDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `deposite_points` (`externalId` INTEGER NOT NULL, `partnerName` TEXT NOT NULL, `imgUrl` TEXT NOT NULL, `hasSeen` INTEGER NOT NULL, `location` TEXT NOT NULL, `workHours` TEXT NOT NULL, `addressInfo` TEXT NOT NULL, `fullAddress` TEXT NOT NULL, `verificationInfo` TEXT NOT NULL, PRIMARY KEY(`externalId`), FOREIGN KEY(`partnerName`) REFERENCES `partners`(`partnerId`) ON UPDATE NO ACTION ON DELETE CASCADE , FOREIGN KEY(`imgUrl`) REFERENCES `partners`(`picture`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        _db.execSQL("CREATE UNIQUE INDEX `index_deposite_points_partnerName_imgUrl` ON `deposite_points` (`partnerName`, `imgUrl`)");
        _db.execSQL("CREATE  INDEX `index_deposite_points_imgUrl` ON `deposite_points` (`imgUrl`)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `partners` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `partnerId` TEXT NOT NULL, `name` TEXT NOT NULL, `picture` TEXT NOT NULL, `url` TEXT NOT NULL, `limitations` TEXT NOT NULL, `depositionDuration` TEXT NOT NULL, `pointType` TEXT NOT NULL, `description` TEXT NOT NULL, `moneyMin` INTEGER NOT NULL, `moneyMax` INTEGER NOT NULL)");
        _db.execSQL("CREATE UNIQUE INDEX `index_partners_picture` ON `partners` (`picture`)");
        _db.execSQL("CREATE UNIQUE INDEX `index_partners_partnerId` ON `partners` (`partnerId`)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"403d7e8189327cb804c5fccdbeeeb0b3\")");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `deposite_points`");
        _db.execSQL("DROP TABLE IF EXISTS `partners`");
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        _db.execSQL("PRAGMA foreign_keys = ON");
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      protected void validateMigration(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsDepositePoints = new HashMap<String, TableInfo.Column>(9);
        _columnsDepositePoints.put("externalId", new TableInfo.Column("externalId", "INTEGER", true, 1));
        _columnsDepositePoints.put("partnerName", new TableInfo.Column("partnerName", "TEXT", true, 0));
        _columnsDepositePoints.put("imgUrl", new TableInfo.Column("imgUrl", "TEXT", true, 0));
        _columnsDepositePoints.put("hasSeen", new TableInfo.Column("hasSeen", "INTEGER", true, 0));
        _columnsDepositePoints.put("location", new TableInfo.Column("location", "TEXT", true, 0));
        _columnsDepositePoints.put("workHours", new TableInfo.Column("workHours", "TEXT", true, 0));
        _columnsDepositePoints.put("addressInfo", new TableInfo.Column("addressInfo", "TEXT", true, 0));
        _columnsDepositePoints.put("fullAddress", new TableInfo.Column("fullAddress", "TEXT", true, 0));
        _columnsDepositePoints.put("verificationInfo", new TableInfo.Column("verificationInfo", "TEXT", true, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysDepositePoints = new HashSet<TableInfo.ForeignKey>(2);
        _foreignKeysDepositePoints.add(new TableInfo.ForeignKey("partners", "CASCADE", "NO ACTION",Arrays.asList("partnerName"), Arrays.asList("partnerId")));
        _foreignKeysDepositePoints.add(new TableInfo.ForeignKey("partners", "CASCADE", "NO ACTION",Arrays.asList("imgUrl"), Arrays.asList("picture")));
        final HashSet<TableInfo.Index> _indicesDepositePoints = new HashSet<TableInfo.Index>(2);
        _indicesDepositePoints.add(new TableInfo.Index("index_deposite_points_partnerName_imgUrl", true, Arrays.asList("partnerName","imgUrl")));
        _indicesDepositePoints.add(new TableInfo.Index("index_deposite_points_imgUrl", false, Arrays.asList("imgUrl")));
        final TableInfo _infoDepositePoints = new TableInfo("deposite_points", _columnsDepositePoints, _foreignKeysDepositePoints, _indicesDepositePoints);
        final TableInfo _existingDepositePoints = TableInfo.read(_db, "deposite_points");
        if (! _infoDepositePoints.equals(_existingDepositePoints)) {
          throw new IllegalStateException("Migration didn't properly handle deposite_points(com.tinkoff.task.repository.data.local.entity.DepositePointL).\n"
                  + " Expected:\n" + _infoDepositePoints + "\n"
                  + " Found:\n" + _existingDepositePoints);
        }
        final HashMap<String, TableInfo.Column> _columnsPartners = new HashMap<String, TableInfo.Column>(11);
        _columnsPartners.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
        _columnsPartners.put("partnerId", new TableInfo.Column("partnerId", "TEXT", true, 0));
        _columnsPartners.put("name", new TableInfo.Column("name", "TEXT", true, 0));
        _columnsPartners.put("picture", new TableInfo.Column("picture", "TEXT", true, 0));
        _columnsPartners.put("url", new TableInfo.Column("url", "TEXT", true, 0));
        _columnsPartners.put("limitations", new TableInfo.Column("limitations", "TEXT", true, 0));
        _columnsPartners.put("depositionDuration", new TableInfo.Column("depositionDuration", "TEXT", true, 0));
        _columnsPartners.put("pointType", new TableInfo.Column("pointType", "TEXT", true, 0));
        _columnsPartners.put("description", new TableInfo.Column("description", "TEXT", true, 0));
        _columnsPartners.put("moneyMin", new TableInfo.Column("moneyMin", "INTEGER", true, 0));
        _columnsPartners.put("moneyMax", new TableInfo.Column("moneyMax", "INTEGER", true, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPartners = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesPartners = new HashSet<TableInfo.Index>(2);
        _indicesPartners.add(new TableInfo.Index("index_partners_picture", true, Arrays.asList("picture")));
        _indicesPartners.add(new TableInfo.Index("index_partners_partnerId", true, Arrays.asList("partnerId")));
        final TableInfo _infoPartners = new TableInfo("partners", _columnsPartners, _foreignKeysPartners, _indicesPartners);
        final TableInfo _existingPartners = TableInfo.read(_db, "partners");
        if (! _infoPartners.equals(_existingPartners)) {
          throw new IllegalStateException("Migration didn't properly handle partners(com.tinkoff.task.repository.data.local.entity.PartnerL).\n"
                  + " Expected:\n" + _infoPartners + "\n"
                  + " Found:\n" + _existingPartners);
        }
      }
    }, "403d7e8189327cb804c5fccdbeeeb0b3", "910ae21123e24467b000da8abb017c1d");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "deposite_points","partners");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    boolean _supportsDeferForeignKeys = android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP;
    try {
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = FALSE");
      }
      super.beginTransaction();
      if (_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA defer_foreign_keys = TRUE");
      }
      _db.execSQL("DELETE FROM `deposite_points`");
      _db.execSQL("DELETE FROM `partners`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = TRUE");
      }
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public DepositePointDao depositePointDao() {
    if (_depositePointDao != null) {
      return _depositePointDao;
    } else {
      synchronized(this) {
        if(_depositePointDao == null) {
          _depositePointDao = new DepositePointDao_Impl(this);
        }
        return _depositePointDao;
      }
    }
  }

  @Override
  public PartnerDao partnerDao() {
    if (_partnerDao != null) {
      return _partnerDao;
    } else {
      synchronized(this) {
        if(_partnerDao == null) {
          _partnerDao = new PartnerDao_Impl(this);
        }
        return _partnerDao;
      }
    }
  }
}
