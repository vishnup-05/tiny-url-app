package com.example.tinyurl.service;

import com.example.tinyurl.model.Url;
import com.example.tinyurl.repository.UrlRepository;
import com.example.tinyurl.util.Base62;
import org.springframework.stereotype.Service;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class UrlService {

    private final UrlRepository urlRepository;

    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public Url shortenUrl(String originalUrl) {
        try {
            String shortUrl = generateShortUrl(originalUrl);
            System.out.println("Short url: "+ shortUrl);
            Url url = new Url();
            url.setOriginalUrl(originalUrl);
            url.setShortUrl(shortUrl);
            return urlRepository.save(url);
        } catch (NoSuchAlgorithmException e) {
            // Handle the exception appropriately
            System.out.print(e.toString());

            return null;
        }
    }

    public Url redirect(String shortUrl) {
        return urlRepository.findByShortUrl(shortUrl);
    }

    private String generateShortUrl(String originalUrl) throws NoSuchAlgorithmException {
        String shortUrl = getShortUrl(originalUrl);
        while (urlRepository.findByShortUrl(shortUrl) != null) {
            shortUrl = getShortUrl(originalUrl + System.currentTimeMillis());
        }
        return shortUrl;
    }

    private String getShortUrl(String url) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(url.getBytes(StandardCharsets.UTF_8));
        ByteBuffer buffer = ByteBuffer.wrap(hash);
        return Base62.encode(buffer.getLong());
    }
}
