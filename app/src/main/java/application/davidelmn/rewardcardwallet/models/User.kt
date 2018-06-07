package application.davidelmn.rewardcardwallet.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class User {

    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("first_name")
    @Expose
    var firstName: String? = null
    @SerializedName("last_name")
    @Expose
    var lastName: String? = null
    @SerializedName("username")
    @Expose
    var username: String? = null
    @SerializedName("city")
    @Expose
    var city: String? = null
    @SerializedName("state")
    @Expose
    var state: String? = null
    @SerializedName("country")
    @Expose
    var country: String? = null
    @SerializedName("location")
    @Expose
    var location: String? = null
    @SerializedName("company")
    @Expose
    var company: String? = null
    @SerializedName("occupation")
    @Expose
    var occupation: String? = null
    @SerializedName("created_on")
    @Expose
    var createdOn: Int? = null
    @SerializedName("url")
    @Expose
    var url: String? = null
    @SerializedName("images")
    @Expose
    var images: Images? = null
    @SerializedName("display_name")
    @Expose
    var displayName: String? = null
    @SerializedName("fields")
    @Expose
    var fields: List<String>? = null
    @SerializedName("has_default_image")
    @Expose
    var hasDefaultImage: Int? = null
    @SerializedName("website")
    @Expose
    var website: String? = null
    @SerializedName("stats")
    @Expose
    var stats: Stats? = null

}
