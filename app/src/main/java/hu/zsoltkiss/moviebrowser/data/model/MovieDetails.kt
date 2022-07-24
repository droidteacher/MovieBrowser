package hu.zsoltkiss.moviebrowser.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MovieDetails(

    @SerializedName("id")
    @Expose
    var id: Int? = null,

    @SerializedName("original_title")
    @Expose
    var originalTitle: String? = null,

    @SerializedName("budget")
    @Expose
    val budget: Int,

    @SerializedName("revenue")
    @Expose
    val revenue: Int,

    @SerializedName("genres")
    val genres: List<Genre>,

    @SerializedName("release_date")
    @Expose
    var releaseDate: String? = null,

    @SerializedName("overview")
    @Expose
    var overview: String? = null,

    @SerializedName("poster_path")
    @Expose
    var posterPath: String? = null,

    @SerializedName("homepage")
    @Expose
    val homepage: String? = null

) {
    val listOfGenres: String
        get() {
            return genres.joinToString(", ") { it.name }
        }
}


data class Genre(
    @SerializedName("id")
    @Expose
    var id: Int,

    @SerializedName("name")
    @Expose
    var name: String

)
