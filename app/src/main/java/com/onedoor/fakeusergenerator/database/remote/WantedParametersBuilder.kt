package com.onedoor.fakeusergenerator.database.remote

data class WantedParametersBuilder(
    val name: Boolean = false,
    val picture: Boolean = false,
    val email: Boolean = false,
    val birthday: Boolean = false
) {
    //TODO find a better way to build the string
    override fun toString(): String {
        var string = ""
        if (name)
            string += "name,"
        if (picture)
            string += "picture,"
        if (email)
            string += "email,"
        if (birthday)
            string += "dob"
        return string
    }
}