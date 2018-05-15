package com.anki.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author routarddev
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Card implements Serializable {

    private Integer cardId;
    private String question;
    private String answer;
}
