package application.davidelmn.rewardcardwallet.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Stats {

    @SerializedName("followers")
    @Expose
    var followers: Int? = null
    @SerializedName("following")
    @Expose
    var following: Int? = null
    @SerializedName("appreciations")
    @Expose
    var appreciations: Int? = null
    @SerializedName("views")
    @Expose
    var views: Int? = null
    @SerializedName("comments")
    @Expose
    var comments: Int? = null

}
