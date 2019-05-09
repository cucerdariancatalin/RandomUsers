package com.juangm.randomusers.data.source.local

import androidx.paging.DataSource
import com.juangm.randomusers.data.mapper.mapLocalUserToDomain
import com.juangm.randomusers.data.source.local.room.UsersDatabase
import com.juangm.randomusers.data.source.local.room.entity.UserEntity
import com.juangm.randomusers.domain.models.User
import io.reactivex.Single

class UsersLocalSourceImpl(private val usersDatabase: UsersDatabase): UsersLocalSource {

    override fun getUsersCountFromDatabase(): Single<Int> = usersDatabase.usersDao().getUsersCount()

    override fun getUsersFromDatabase(): DataSource.Factory<Int, User> = usersDatabase.usersDao().getUsers()
        .mapByPage { users -> users.map(mapLocalUserToDomain) }

    override fun saveUsersInDatabase(users: List<UserEntity>) = usersDatabase.usersDao().insertUsers(users)
}