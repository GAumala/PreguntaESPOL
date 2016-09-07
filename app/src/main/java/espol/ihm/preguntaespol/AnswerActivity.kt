package espol.ihm.preguntaespol

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.criptext.monkeykitui.dialog.DialogOption
import com.criptext.monkeykitui.input.attachment.AttachmentButton
import com.criptext.monkeykitui.input.listeners.InputListener
import com.criptext.monkeykitui.recycler.MonkeyItem
import com.mikepenz.actionitembadge.library.ActionItemBadge
import com.rengwuxian.materialedittext.MaterialEditText

/**
 * Created by gesuwall on 9/3/16.
 */

open class AnswerActivity: AppCompatActivity() {

    lateinit var descEditText: MaterialEditText
    lateinit var attachmentButton: AttachmentButton
    private var photoPath: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initLayout()
        val toolbar =  findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar);
        setupAttachmentButton()
    }

    fun setupAttachmentButton(){
        attachmentButton = AttachmentButton(this)
        attachmentButton.attachmentOptions.clear()
        attachmentButton.attachmentOptions.add(object : DialogOption("Tomar foto") {
            override fun onOptionSelected() {
                attachmentButton.cameraHandler.takePicture()
            }
        })
        attachmentButton.attachmentOptions.add(object : DialogOption("Abrir galería") {
            override fun onOptionSelected() {
                attachmentButton.cameraHandler.pickFromGallery()
            }
        })

        attachmentButton.inputListener = object: InputListener {
            override fun onNewItem(item: MonkeyItem) {
                photoPath = item.getFilePath()
                invalidateOptionsMenu()
                attachmentButton.attachmentOptions.add(object: DialogOption("Remover foto") {
                    override fun onOptionSelected() {
                        photoPath = null
                        attachmentButton.attachmentOptions.removeAt(2)
                        invalidateOptionsMenu()
                    }
                })
            }

        }
    }
    open fun initLayout(){
        setContentView(R.layout.activity_answer)
        descEditText = findViewById(R.id.desc_edittext) as MaterialEditText
    }

    open fun validarInputs(): Intent? {
        var isValid = true

        val descStr = descEditText.text
        if(descStr.isEmpty()){
            descEditText.error = "Por favor ingresa una descripción"
            isValid = false
        }

        if(isValid){
            val intent = Intent()
            if(photoPath != null)
                intent.putExtra(PHOTO_KEY, photoPath)
            intent.putExtra(CONTENT_KEY, descStr.toString())
            return intent
        }

        return null
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.action_send -> {
                val intent = validarInputs()
                if(intent != null) {
                    setResult(Activity.RESULT_OK, intent)
                    finish()
                }
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        attachmentButton.cameraHandler.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.editor_actions, menu);
        val badge = if(photoPath != null) "1" else null
        ActionItemBadge.update(this, menu.findItem(R.id.action_attach),
                ContextCompat.getDrawable(this, R.drawable.ic_attach),
                ActionItemBadge.BadgeStyles.RED.style, badge, ActionItemBadge.ActionItemBadgeListener {
                    attachmentButton.performClick()
                    true
                })
        return true;
    }

    companion object {
        val CONTENT_KEY = "AskActivity.content"
        val PHOTO_KEY = "AskActivity.photopath"
    }
}