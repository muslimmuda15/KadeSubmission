package relawan.kade2.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DetailTeam(
    val idLeague: String?,
    val idTeam: String?,
    val intFormedYear: String?,
    val strCountry: String?,
    val strDescriptionEN: String?,
    val strStadium: String?,
    val strTeam: String?,
    val strTeamBadge: String?,
    val strTeamBanner: String?
): Parcelable