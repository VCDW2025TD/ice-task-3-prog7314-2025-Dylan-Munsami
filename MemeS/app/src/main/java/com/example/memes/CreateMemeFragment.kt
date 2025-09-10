package com.example.memes

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class CreateMemeFragment : Fragment() {

    private lateinit var repository: MemeRepository

    private lateinit var ivSelectedMeme: ImageView
    private lateinit var etCaption: EditText
    private lateinit var btnUpload: Button
    private var selectedImageUri: Uri? = null
    private val PICK_IMAGE = 101
    private val currentUserId = "user123" // placeholder user ID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        repository = MemeRepository(
            requireContext(),
            apiService = TODO()
        ) // <-- offline-only repository
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ivSelectedMeme = view.findViewById(R.id.ivSelectedMeme)
        etCaption = view.findViewById(R.id.etCaption)
        btnUpload = view.findViewById(R.id.btnUploadMeme)

        ivSelectedMeme.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, PICK_IMAGE)
        }

        btnUpload.setOnClickListener {
            val captionInput = etCaption.text.toString()
            val imageUri = selectedImageUri?.toString() ?: ""
            if (captionInput.isEmpty() || imageUri.isEmpty()) {
                Toast.makeText(context, "Select an image and add caption", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val meme = Meme(
                userId = currentUserId,
                caption = captionInput,
                imageUrl = imageUri
            )

            lifecycleScope.launch {
                repository.addMeme(meme) // save offline in Room
                Toast.makeText(context, "Saved offline", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK) {
            selectedImageUri = data?.data
            ivSelectedMeme.setImageURI(selectedImageUri)
        }
    }
}
