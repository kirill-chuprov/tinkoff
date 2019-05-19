package com.tinkoff.task.repository.data.local.db;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.RxRoom;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.tinkoff.task.repository.data.local.entity.EntityL;
import io.reactivex.Flowable;
import java.lang.Exception;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class EntityDao_Impl implements EntityDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfEntityL;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public EntityDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfEntityL = new EntityInsertionAdapter<EntityL>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `EntityL`(`dbId`,`id`,`data`) VALUES (nullif(?, 0),?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, EntityL value) {
        stmt.bindLong(1, value.getDbId());
        if (value.getId() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getId());
        }
        if (value.getData() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getData());
        }
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM EntityL";
        return _query;
      }
    };
  }

  @Override
  public long insert(final EntityL entity) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      long _result = __insertionAdapterOfEntityL.insertAndReturnId(entity);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<Long> insertAll(final List<EntityL> entityList) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      List<Long> _result = __insertionAdapterOfEntityL.insertAndReturnIdsList(entityList);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public int deleteAll() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAll.acquire();
    __db.beginTransaction();
    try {
      final int _result = _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAll.release(_stmt);
    }
  }

  @Override
  public Flowable<List<EntityL>> getAll() {
    final String _sql = "SELECT * FROM EntityL ORDER BY dbId ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return RxRoom.createFlowable(__db, false, new String[]{"EntityL"}, new Callable<List<EntityL>>() {
      @Override
      public List<EntityL> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false);
        try {
          final int _cursorIndexOfDbId = CursorUtil.getColumnIndexOrThrow(_cursor, "dbId");
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfData = CursorUtil.getColumnIndexOrThrow(_cursor, "data");
          final List<EntityL> _result = new ArrayList<EntityL>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final EntityL _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpData;
            _tmpData = _cursor.getString(_cursorIndexOfData);
            _item = new EntityL(_tmpId,_tmpData);
            final int _tmpDbId;
            _tmpDbId = _cursor.getInt(_cursorIndexOfDbId);
            _item.setDbId(_tmpDbId);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flowable<List<EntityL>> find(final int id) {
    final String _sql = "SELECT * FROM EntityL WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    return RxRoom.createFlowable(__db, false, new String[]{"EntityL"}, new Callable<List<EntityL>>() {
      @Override
      public List<EntityL> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false);
        try {
          final int _cursorIndexOfDbId = CursorUtil.getColumnIndexOrThrow(_cursor, "dbId");
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfData = CursorUtil.getColumnIndexOrThrow(_cursor, "data");
          final List<EntityL> _result = new ArrayList<EntityL>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final EntityL _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpData;
            _tmpData = _cursor.getString(_cursorIndexOfData);
            _item = new EntityL(_tmpId,_tmpData);
            final int _tmpDbId;
            _tmpDbId = _cursor.getInt(_cursorIndexOfDbId);
            _item.setDbId(_tmpDbId);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flowable<List<EntityL>> findById(final String id) {
    final String _sql = "SELECT * FROM EntityL WHERE id LIKE '%' || ? || '%'";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (id == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, id);
    }
    return RxRoom.createFlowable(__db, false, new String[]{"EntityL"}, new Callable<List<EntityL>>() {
      @Override
      public List<EntityL> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false);
        try {
          final int _cursorIndexOfDbId = CursorUtil.getColumnIndexOrThrow(_cursor, "dbId");
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfData = CursorUtil.getColumnIndexOrThrow(_cursor, "data");
          final List<EntityL> _result = new ArrayList<EntityL>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final EntityL _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpData;
            _tmpData = _cursor.getString(_cursorIndexOfData);
            _item = new EntityL(_tmpId,_tmpData);
            final int _tmpDbId;
            _tmpDbId = _cursor.getInt(_cursorIndexOfDbId);
            _item.setDbId(_tmpDbId);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }
}
