package de.fzj.atlascore.tvb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/brain/dummy")
public class TVBDummyDataController implements ITVBController {

    @Autowired
    private DummyDataService dummyDataService;

    @Override
    public ResponseEntity<List<String>> getAllNodes() {
        try {
            return new ResponseEntity<>(dummyDataService.getAllNodes(), HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ResponseEntity<String> getInformationForNode(String nodeValue) {
        return null;
    }

    @Override
    public ResponseEntity<String> getAreaForNode(String nodeValue) {
        try {
            return new ResponseEntity<>(dummyDataService.getAreaForNode(nodeValue), HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ResponseEntity<String> getAverageOrientationForNode(String nodeValue) {
        try {
            return new ResponseEntity<>(dummyDataService.getAverageOrientationForNode(nodeValue), HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ResponseEntity<String> getCentresForNode(String nodeValue) {
        try {
            return new ResponseEntity<>(dummyDataService.getCentreForNode(nodeValue), HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ResponseEntity<String> isCorticalForNode(String nodeValue) {
        try {
            return new ResponseEntity<>(dummyDataService.getCorticalForNode(nodeValue), HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ResponseEntity<String> getTractLengthsForNode(String nodeValue) {
        try {
            return new ResponseEntity<>(dummyDataService.getTractLengthForNode(nodeValue), HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ResponseEntity<String> getVolumesForNode(String nodeValue) {
        try {
            return new ResponseEntity<>(dummyDataService.getVolumeForNode(nodeValue), HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ResponseEntity<String> getWeightsForNode(String nodeValue) {
        try {
            return new ResponseEntity<>(dummyDataService.getWeightsForNode(nodeValue), HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
