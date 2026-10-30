// -*- mode: java; c-basic-offset: 2; -*-
// Copyright 2026 MIT, All rights reserved
// Released under the Apache License, Version 2.0
// http://www.apache.org/licenses/LICENSE-2.0

package com.google.appinventor.components.runtime;

import static org.junit.Assert.assertEquals;

import com.google.appinventor.components.runtime.shadows.ShadowEventDispatcher;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import org.junit.Test;

/**
 * Tests for the Sharing component.
 */
public class SharingTest extends RobolectricTestBase {
  private static final String MISSING_FILE = "does_not_exist.png";

  @Test
  public void testShareFileReportsShareFileWhenMissing() {
    new Sharing(getForm()).ShareFile(MISSING_FILE);
    assertErrorMethod("ShareFile");
  }

  @Test
  public void testShareFileWithMessageReportsShareFileWithMessageWhenMissing() {
    new Sharing(getForm()).ShareFileWithMessage(MISSING_FILE, "hello");
    assertErrorMethod("ShareFileWithMessage");
  }

  @Test
  public void testShareFileWithEmptyMessageReportsShareFileWithMessage() {
    new Sharing(getForm()).ShareFileWithMessage(MISSING_FILE, "");
    assertErrorMethod("ShareFileWithMessage");
  }

  private void assertErrorMethod(String expected) {
    Object[] args = ShadowEventDispatcher.getArgumentsForEventFired(getForm(), "ErrorOccurred");
    assertEquals(ErrorMessages.ERROR_FILE_NOT_FOUND_FOR_SHARING, args[2]);
    assertEquals(expected, args[1]);
  }
}
