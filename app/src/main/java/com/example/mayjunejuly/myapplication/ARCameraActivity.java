package com.example.mayjunejuly.myapplication;

import android.os.Bundle;
import android.view.View;

import java.util.List;

import eu.kudan.kudan.ARActivity;
import eu.kudan.kudan.ARAlphaVideoNode;
import eu.kudan.kudan.ARImageNode;
import eu.kudan.kudan.ARImageTrackable;
import eu.kudan.kudan.ARImageTrackableListener;
import eu.kudan.kudan.ARImageTracker;
import eu.kudan.kudan.ARLightMaterial;
import eu.kudan.kudan.ARMeshNode;
import eu.kudan.kudan.ARModelImporter;
import eu.kudan.kudan.ARModelNode;
import eu.kudan.kudan.ARNode;
import eu.kudan.kudan.ARRenderer;
import eu.kudan.kudan.ARTexture2D;
import eu.kudan.kudan.ARTextureMaterial;
import eu.kudan.kudan.ARVideoNode;
import eu.kudan.kudan.ARVideoTexture;
import eu.kudan.kudan.ARView;

public class ARCameraActivity extends ARActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arcamera);
    }

    public void setup() {
        addImageTrackable();
    }

    private void addImageTrackable() {

        // Initialise image trackable
        ARImageTrackable manora = new ARImageTrackable("manora");
        manora.loadFromAsset("manora.jpg");
        ARImageTrackable pocky = new ARImageTrackable("pocky");
        pocky.loadFromAsset("pocky.jpg");
        // Get instance of image tracker manager
        ARImageTracker trackableManager = ARImageTracker.getInstance();
        trackableManager.initialise();
        // Add image trackable to image tracker manager
        trackableManager.addTrackable(manora);
        trackableManager.addTrackable(pocky);
        // Initialise image node
        ARImageNode nadech = new ARImageNode("nadech-sale.png");
        // Add image node to image trackable
        manora.getWorld().addChild(nadech);
        ARTextureMaterial textureMaterial = (ARTextureMaterial)nadech.getMaterial();
        float scale = manora.getWidth() / textureMaterial.getTexture().getWidth();
        nadech.scaleByUniform(scale);
        // Initialise image node
        ARImageNode cow = new ARImageNode("eyebrow.png");
        // Add image node to image trackable
        pocky.getWorld().addChild(cow);
        ARTextureMaterial texture = (ARTextureMaterial)cow.getMaterial();
        float scales = pocky.getWidth() / texture.getTexture().getWidth();
        cow.scaleByUniform(scales);
    }

}
