package net.scootergo.app.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Scooter(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String? = null,
    val icon: String? = null,
    val unlockCost: Double? = 0.0,
    val minuteCost: Double? = 0.0,
    val playPackage: String? = null,
) : Parcelable