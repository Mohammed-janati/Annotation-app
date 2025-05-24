package com.annotateurproject.services;

import com.annotateurproject.DTO.DatasetResponse;
import com.annotateurproject.DTO.DatasetWithAvencement;
import com.annotateurproject.entity.*;
import com.annotateurproject.mapper.customMapper;
import com.annotateurproject.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class adminServiceImpl implements adminService {



@Autowired
customMapper customMapper;
    @Autowired
    DatasetRepo datasetRepo;
    @Autowired
    coupleTexteRepo coupleTexteRepo;
    @Autowired
    annotatorRepo annotatorRepo;
    @Autowired
    tachRepo tachRepo;
    @Autowired
    anotationRepo annotationRepo;


    public void createDataset(MultipartFile file, String name, String className, String description) throws IOException {
        Dataset d=new Dataset();
        d.setDescription(description);
d.setName(name);

        //extract classes name
        List<String> c= Arrays.asList(className.split(";"));
      List<possibleClass> p= c.stream().map((x)->{
          possibleClass pc=new possibleClass(x);
          pc.setDataset(d);
          return pc;
      }).collect(Collectors.toList());

      //seting dataset classes
        d.setClasses(p);

        //settiing couple tesxt
        d.setCoupleTexte(getdata(file,d));

        datasetRepo.save(d);


    }

    @Override
    public void affecteAnnotator(List<Integer> annotators, int id) {

       Dataset d= datasetRepo.findById(id).orElseThrow(()->new RuntimeException("Dataset not found will affecting annotator"));

       List<coupleTexte> texte= coupleTexteRepo.findAllByDataset(d);
       Collections.shuffle(texte);

int size= texte.size()/ annotators.size();
AtomicInteger i= new AtomicInteger();

      List<tache> taches= annotators.stream().map((x)->{
    annotator a=annotatorRepo.findById(x).orElseThrow(()->new RuntimeException("Annotator not found will affecting annotator"));
           tache t=new tache();
           t.setDataset(d);
           t.setAnnotator(a);
           t.setDate(LocalDateTime.now().plusDays(2));
        List<coupleTexte> sub= texte.subList(i.get(), i.addAndGet(size));
    t.setCoupletexte(sub);

    //affect tache to coUple teste

          List<coupleTexte> v=   sub.stream().map((y)->{
              y.setTache(t);

              return y;
          }).toList();
            tachRepo.save(t);
           return t;
       }).toList();
    }


    @Override
    public List<DatasetWithAvencement> showDataset() {

        var x=datasetRepo.findAll();

return x.stream().map(c->{
    DatasetWithAvencement a=new DatasetWithAvencement();
a.setDataset(c);
    a.setAvencement((int) (((float)this.getDatasetAvancement(c.getId())/this.getDatasetCount(c.getId()))*100));
    return a;
}).toList();

    }

    @Override
    public List<annotator> showAnnotator() {
        return annotatorRepo.findAllByNotActive(true);
    }

    @Override
    public DatasetResponse getDataset(int id) {

              Dataset a= datasetRepo.findById(id).orElseThrow(()->new RuntimeException("Dataset not found will affecting annotator"));
              return customMapper.datasetTODatasetResponse(a);
    }

    @Override
    public Page<coupleTexte> getPaginatedTexte(int id, int page, int size) {
        Pageable p= PageRequest.of(page, size);
        return coupleTexteRepo.findByDatasetId(id,p);
    }

    @Override
    public void deleteAnotator(int id) {
         annotator a=annotatorRepo.findById(id).orElseThrow(()->new RuntimeException("Annotator not found will affecting annotator"));
        a.setNotActive(false);
        annotatorRepo.save(a);
    }

    @Override
    public annotator getAnnotatorById(int id) {
       return  annotatorRepo.findById(id).orElseThrow(()->new RuntimeException("Annotator not found will affecting annotator"));
    }

    @Override
    public void modifyAnotator(annotator id) {
        System.out.println(id);
        annotator a=annotatorRepo.findById(id.getId()).orElseThrow(()->new RuntimeException("Annotator not found will affecting annotator"));
        a.setNotActive(id.isNotActive());
        a.setPassword(a.getPassword());
        a.setRole(a.getRole());
        a.setLastName(id.getLastName());
        a.setFirstName(id.getFirstName());
        a.setEmail(id.getEmail());

        annotatorRepo.save(a);
    }

    @Override
    public List<annotator> getAnnotatorPerDataset(int id) {

return tachRepo.findAllByDatasetId(id);

    }

    @Override
    public void deleteTache(int id) {
        tachRepo.deleteByAnnotatorId(id);
    }

    @Override
    public int getDatasetCount(int id) {
        return coupleTexteRepo.countByDataset_Id(id);
    }

    @Override
    public int getDatasetAvancement(int id) {
        List<Integer> texteId=coupleTexteRepo.findcoupleTexteIdByDatasetId(id);
        int count=annotationRepo.countByCoupleTexte_IdIn(texteId);
        return count;
    }

    @Override
    public Resource getRessource(int id) {

        StringWriter sw=new StringWriter();
        PrintWriter pw=new PrintWriter(sw);
        pw.println("text1,texte2,label1");

        var l=coupleTexteRepo.findByDatasetId(id);
        l.forEach(c->{
            var annotation =annotationRepo.findByCoupleTexteId(c.getId());
            pw.printf("%s, %s, %s%n",c.getT1(),c.getT2(),annotation.getChosenClass());

        });

        pw.flush();
        ByteArrayResource resource = new ByteArrayResource(sw.toString().getBytes());
return  resource;
    }


    public List<coupleTexte> getdata(MultipartFile data,Dataset d ) throws IOException {

        List<String[]> rows = new ArrayList<>();
        List<coupleTexte> coupleTextes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(data.getInputStream()))) {
            String line;


            while ((line = br.readLine()) != null) {
                String[] columns = line.split(","); // assuming columns are separated by semicolons
                if (columns.length >= 2) {

                    rows.add(new String[]{columns[0], columns[1]});
                }
            }

            coupleTextes= rows.stream().map((x)->{
                coupleTexte coupleTexte=new coupleTexte();
                coupleTexte.setT1(x[0]);
                coupleTexte.setT2(x[1]);
                coupleTexte.setDataset(d);
                return coupleTexte;
            }).collect(Collectors.toList());

        }


        return coupleTextes;
    }


}


