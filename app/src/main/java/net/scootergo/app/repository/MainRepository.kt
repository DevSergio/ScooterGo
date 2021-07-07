package net.scootergo.app.repository

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import net.scootergo.app.model.Scooter
import net.scootergo.app.model.ScooterResponse
import net.scootergo.app.utils.Constants.PRODUCTS_REF
import kotlinx.coroutines.tasks.await
import net.scootergo.app.persistence.ScooterDao
import javax.inject.Inject

class MainRepository @Inject constructor(private val scooterDao: ScooterDao) : Repository {

    private val rootRef: DatabaseReference = FirebaseDatabase.getInstance().reference
    private val productRef: DatabaseReference = rootRef.child(PRODUCTS_REF)

    suspend fun getScooterList(): ScooterResponse {
        val response = ScooterResponse()

        val scooters = scooterDao.getScooterList()
        if (scooters.isEmpty()) {
            try {
                response.products = productRef.get().await().children.map { snapShot ->
                    snapShot.getValue(Scooter::class.java)!!
                }
                scooterDao.insertScooterList(response.products!!)
            } catch (exception: Exception) {
                response.exception = exception
            }
        } else {
            response.products = scooters
        }
        return response
    }
}