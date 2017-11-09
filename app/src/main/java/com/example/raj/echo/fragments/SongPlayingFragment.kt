package com.example.raj.echo.fragments


import android.app.Activity
import android.content.Context
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.SeekBar
import android.widget.TextView
import com.example.raj.echo.R


/**
 * A simple [Fragment] subclass.
 */
class SongPlayingFragment : Fragment() {
    var myActivity: Activity?=null
    var mediaPlayer:MediaPlayer?=null
    var startTimeNext:TextView?=null
    var endTimeNext: TextView?=null
    var playpauseImageButton: ImageButton?=null
    var previousImageButton: ImageButton?=null
    var nextImageButton: ImageButton?=null
    var loopImageButton: ImageButton?=null
    var seekbar: SeekBar?=null
    var songArtistView: TextView?=null
    var shuffleImageButton: ImageButton?=null
    var songTitleView: TextView?=null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var view = inflater!!.inflate(R.layout.fragment_song_playing, container, false)
        seekbar=view?.findViewById(R.id.seekBar)
        startTimeNext=view?.findViewById(R.id.startTime)
        endTimeNext=view?.findViewById(R.id.endTime)
        playpauseImageButton=view?.findViewById(R.id.playPauseButton)
        previousImageButton=view?.findViewById(R.id.previousButton)
        nextImageButton=view?.findViewById(R.id.nextButton)
        loopImageButton=view?.findViewById(R.id.loopButton)
        songArtistView=view?.findViewById(R.id.songArtist)
        shuffleImageButton=view?.findViewById(R.id.shuffleButton)
        songTitleView=view?.findViewById(R.id.songTitle)

        return view
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        myActivity = context as Activity
    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        myActivity = activity
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var path: String?= null
        var _songTitle: String?=null
        var _songArtist: String?=null
        var _sondID: Long = 0
        try{
            path = arguments.getString("path")
            _songTitle = arguments.getString("songTitle")
            _songArtist = arguments.getString("songArtist")
            _sondID = arguments.getInt("songID").toLong()
    }
        catch(e : Exception){
        e.printStackTrace()
        }
        mediaPlayer = MediaPlayer()
        mediaPlayer?.setAudioStreamType(AudioManager.STREAM_MUSIC)
        try{
            mediaPlayer?.setDataSource(myActivity,Uri.parse(path))
            mediaPlayer?.prepare()
        }
        catch (e: Exception){
            e.printStackTrace()
        }
        mediaPlayer?.start()
    }
    fun onClickHandler(){
        shuffleImageButton?.setOnClickListener({})
        nextImageButton?.setOnClickListener({})
        previousImageButton?.setOnClickListener({})
        playpauseImageButton?.setOnClickListener({
            if(mediaPlayer?.isPlaying as Boolean){
                mediaPlayer?.pause()
                playpauseImageButton?.setBackgroundResource(R.drawable.play_icon)
            }else{
                mediaPlayer?.start()
                playpauseImageButton?.setBackgroundResource(R.drawable.pause_icon)
            }
        })
        loopImageButton?.setOnClickListener({})

    }

}// Required empty public constructor
