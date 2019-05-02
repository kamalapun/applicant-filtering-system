package com.cvfiltering.project.cvfiltering.service.Implement;

import com.cvfiltering.project.cvfiltering.entity.Languages;
import com.cvfiltering.project.cvfiltering.repository.LanguageRepository;
import com.cvfiltering.project.cvfiltering.service.LanguagesService;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Data
public class LanguagesServiceImpl implements LanguagesService {
    private LanguageRepository languageRepository;
    @Override
    public Languages insert(Languages languages) {
        Languages languages1 = languageRepository.save(languages);
        return languages1;
    }
}
