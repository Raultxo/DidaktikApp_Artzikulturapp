package com.example.didaktikappartzikulturapp;

import android.content.Context;
import androidx.appcompat.app.*;

public class Act1_PuzzlePiece extends androidx.appcompat.widget.AppCompatImageView  {
    public int xCoord;
    public int yCoord;
    public int pieceWidth;
    public int pieceHeight;
    public boolean canMove = true;

    public Act1_PuzzlePiece(Context context) {
        super(context);
    }
}