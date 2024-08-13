/*
 * Copyright © 2023 FinToken Ltd.,  All rights reserved.
 */

package org.example.feettool.info;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

/**
 * description
 *
 * @author zhengguangle
 * @version create
 * @since 2023-02-05
 */
@Data
public class ValidList<T> {
	@Valid
	@NotNull
	@Size(min = 1, max = 100)
	List<T> list;
}
