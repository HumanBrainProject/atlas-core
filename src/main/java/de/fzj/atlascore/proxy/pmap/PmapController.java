package de.fzj.atlascore.proxy.pmap;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("pmap")
public class PmapController {

    private final PmapService pmapService;

    public PmapController(PmapService pmapService) {
        this.pmapService = pmapService;
    }

    @GetMapping(value = "/regions")
    public String[] getPmapRegions() {
        return pmapService.getAllRegions();
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
    )
    public HttpEntity<byte[]> retrieveProbabilityMap(@RequestParam(required = false) String filename, @RequestBody Object data, HttpServletResponse response) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        response.setHeader(
                "Content-Disposition",
                "attachment; filename=" + (StringUtils.isEmpty(filename) ? "merged_volume" : filename) + ".nii.gz");

        return new HttpEntity<byte[]>(pmapService.getPmap(data), headers);
    }
}
